package ru.tecius.util;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {

    SYSTEM_ERROR(INTERNAL_SERVER_ERROR.value()),
    SERVICE_UNAVAILABLE(550),
    TIMEOUT(551),
    VALIDATION_ERROR(BAD_REQUEST.value()),
    ILLEGAL_ARGUMENT(BAD_REQUEST.value()),
    NOT_FOUND(HttpStatus.NOT_FOUND.value()),
    ILLEGAL_OPERATION(BAD_REQUEST.value()),
    DISABLED(BAD_REQUEST.value()),
    USER_ALREADY_EXISTS(BAD_REQUEST.value()),
    TEMPORARILY_DISABLED(BAD_REQUEST.value()),
    TOO_MANY_REQUESTS(BAD_REQUEST.value()),
    DUPLICATES(BAD_REQUEST.value()),
    INVALID_CREDENTIALS(UNAUTHORIZED.value()),
    INSUFFICIENT_PRIVILEGES(FORBIDDEN.value());

    private final int httpCode;

}
