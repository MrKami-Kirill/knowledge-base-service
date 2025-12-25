package ru.tecius.presentation.controller.handler;


import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;
import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static ru.tecius.util.Constants.COMMA_DELIMITER;
import static ru.tecius.util.Constants.X_REQUEST_ID;
import static ru.tecius.util.Error.EX_SYSTEM_ERROR;
import static ru.tecius.util.Error.EX_VALIDATION_ERROR;

import jakarta.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.tecius.api.dto.error.ErrorResponseDto;
import ru.tecius.config.logger.LoggerDecorator;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

    public static final String EXCEPTION_LOG = "x-request-id = %s | Exception: %s";

    private final LoggerDecorator logger;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException ex,
                                                                            WebRequest request) {
        return logErrorAndReturnResponse(ex, request, BAD_REQUEST.value(), EX_VALIDATION_ERROR.name());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex, WebRequest request) {
        return logErrorAndReturnResponse(ex, request, INTERNAL_SERVER_ERROR.value(), EX_SYSTEM_ERROR.name());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException ex, WebRequest request) {
        return logErrorAndReturnResponse(ex, request, BAD_REQUEST.value(), EX_VALIDATION_ERROR.name());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
                                                                               WebRequest request) {
        return logErrorAndReturnResponse(ex, request, BAD_REQUEST.value(), EX_VALIDATION_ERROR.name());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex,
                                                                        WebRequest request) {
        return logErrorAndReturnResponse(ex, request, BAD_REQUEST.value(), EX_VALIDATION_ERROR.name());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                               WebRequest request) {
        logException(ex, request);
        return prepareResponse(getLocalizedMessage(ex), BAD_REQUEST.value(), EX_VALIDATION_ERROR.name(),
                getStackTrace(ex), request.getHeader(X_REQUEST_ID));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingRequestHeaderException(MissingRequestHeaderException ex,
                                                                             WebRequest request) {
        return logErrorAndReturnResponse(ex, request, BAD_REQUEST.value(), EX_VALIDATION_ERROR.name());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDto> handleNoSuchElementException(NoSuchElementException ex,
                                                                      WebRequest request) {
        return logErrorAndReturnResponse(ex, request, BAD_REQUEST.value(), EX_VALIDATION_ERROR.name());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return logErrorAndReturnResponse(ex, request, INTERNAL_SERVER_ERROR.value(), EX_SYSTEM_ERROR.name());
    }

    private String getLocalizedMessage(MethodArgumentNotValidException ex) {
        return Arrays.stream(ex.getBindingResult().getAllErrors().toArray())
                .map(exc -> {
                    var error = ((DefaultMessageSourceResolvable) exc);
                    return error.getDefaultMessage();
                })
                .collect(joining(COMMA_DELIMITER));
    }

    private ResponseEntity<ErrorResponseDto> logErrorAndReturnResponse(Throwable ex, WebRequest request,
                                                                    int status, String error) {
        logException(ex, request);
        return prepareResponse(ofNullable(ex.getCause()).orElse(ex).getMessage(), status, error,
                getStackTrace(ex), request.getHeader(X_REQUEST_ID));
    }

    private ResponseEntity<ErrorResponseDto> logErrorAndReturnResponse(Exception e, WebRequest request,
                                                                    String msg, int status, String error) {
        logException(e, request);
        return prepareResponse(isNoneBlank(msg) ? msg : ofNullable(e.getCause()).orElse(e).getMessage(),
                status, error, getStackTrace(e), request.getHeader(X_REQUEST_ID));
    }

    private ResponseEntity<ErrorResponseDto> prepareResponse(String msg, int status, String error,
                                                          String stackTrace, String xRequestId) {
        return ResponseEntity
                .status(status)
                .header(X_REQUEST_ID, xRequestId)
                .body(new ErrorResponseDto(error, msg, stackTrace));
    }

    private void logException(Throwable ex, WebRequest request) {
        logger.error(format(EXCEPTION_LOG, request.getHeader(X_REQUEST_ID),
                ofNullable(ex.getCause()).orElse(ex).getMessage()), ex);
    }

}
