package ru.tecius.presentation.controller.handler;


import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;
import static ru.tecius.util.Constants.COMMA_DELIMITER;
import static ru.tecius.util.Constants.X_REQUEST_ID;
import static ru.tecius.util.Error.SYSTEM_ERROR;
import static ru.tecius.util.Error.VALIDATION_ERROR;

import jakarta.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.UUID;
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
import ru.tecius.configuration.logger.LoggerDecorator;
import ru.tecius.util.Error;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

  public static final String EXCEPTION_LOG = "x-request-id = %s | Exception: %s";

  private final LoggerDecorator logger;

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(
      ConstraintViolationException ex,
      WebRequest request) {
    return logErrorAndReturnResponse(ex, request, VALIDATION_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleException(Exception ex, WebRequest request) {
    return logErrorAndReturnResponse(ex, request, SYSTEM_ERROR);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorResponseDto> handleHttpMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException ex, WebRequest request) {
    return logErrorAndReturnResponse(ex, request, VALIDATION_ERROR);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex,
      WebRequest request) {
    return logErrorAndReturnResponse(ex, request, VALIDATION_ERROR);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(
      IllegalArgumentException ex,
      WebRequest request) {
    return logErrorAndReturnResponse(ex, request, VALIDATION_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex,
      WebRequest request) {
    logException(ex, request);
    return prepareResponse(VALIDATION_ERROR, getLocalizedMessage(ex), getStackTrace(ex),
        request.getHeader(X_REQUEST_ID));
  }

  @ExceptionHandler(MissingRequestHeaderException.class)
  public ResponseEntity<ErrorResponseDto> handleMissingRequestHeaderException(
      MissingRequestHeaderException ex,
      WebRequest request) {
    return logErrorAndReturnResponse(ex, request, VALIDATION_ERROR);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorResponseDto> handleNoSuchElementException(NoSuchElementException ex,
      WebRequest request) {
    return logErrorAndReturnResponse(ex, request, VALIDATION_ERROR);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex,
      WebRequest request) {
    return logErrorAndReturnResponse(ex, request, SYSTEM_ERROR);
  }

  private String getLocalizedMessage(MethodArgumentNotValidException ex) {
    return Arrays.stream(ex.getBindingResult().getAllErrors().toArray())
        .map(exc -> {
          var error = ((DefaultMessageSourceResolvable) exc);
          return error.getDefaultMessage();
        })
        .collect(joining(COMMA_DELIMITER));
  }

  private ResponseEntity<ErrorResponseDto> logErrorAndReturnResponse(Throwable ex,
      WebRequest request,
      Error error) {
    logException(ex, request);
    return prepareResponse(error, ofNullable(ex.getCause()).orElse(ex).getMessage(),
        getStackTrace(ex), request.getHeader(X_REQUEST_ID));
  }

  private ResponseEntity<ErrorResponseDto> prepareResponse(
      Error error,
      String msg,
      String stackTrace,
      String xRequestId
  ) {
    return ResponseEntity
        .status(error.getHttpCode())
        .header(X_REQUEST_ID, xRequestId)
        .body(new ErrorResponseDto(error.name(), msg, UUID.randomUUID(), stackTrace));
  }

  private void logException(Throwable ex, WebRequest request) {
    logger.error(format(EXCEPTION_LOG, request.getHeader(X_REQUEST_ID),
        ofNullable(ex.getCause()).orElse(ex).getMessage()), ex);
  }

}
