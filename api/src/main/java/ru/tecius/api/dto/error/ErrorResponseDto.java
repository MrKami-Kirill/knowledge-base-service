package ru.tecius.api.dto.error;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Стандартизированный ответ об ошибке")
public record ErrorResponseDto(
    @Schema(description = "Код ошибки", example = "EX_SYSTEM_ERROR", requiredMode = REQUIRED)
    String error,
    @Schema(description = "Текст ошибки", example = "System error", requiredMode = REQUIRED)
    String message,
    @Schema(description = "Stack Trace ошибки", requiredMode = NOT_REQUIRED)
    String stackTrace
) {

}
