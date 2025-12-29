package ru.tecius.api.dto.error;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Стандартизированный ответ об ошибке")
public record ErrorResponseDto(
    @Schema(description = "Код ошибки", example = "SYSTEM_ERROR", requiredMode = REQUIRED)
    String errorCode,
    @Schema(description = "Текст ошибки", example = "System error", requiredMode = REQUIRED)
    String message,
    @Schema(description = "Trace ID ошибки", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID traceId,
    @Schema(description = "Stack Trace ошибки", requiredMode = NOT_REQUIRED)
    String stackTrace
) {

}
