package ru.tecius.api.dto.document.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для получение данных документа")
public record DocumentResponseDto(DocumentDataDto data) {

}
