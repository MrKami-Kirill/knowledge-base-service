package ru.tecius.api.dto.document.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для получение данных документа")
public record DocumentResponseDto(
    @Schema(description = "Данные документа", requiredMode = REQUIRED)
    DocumentDataDto data
) {

}
