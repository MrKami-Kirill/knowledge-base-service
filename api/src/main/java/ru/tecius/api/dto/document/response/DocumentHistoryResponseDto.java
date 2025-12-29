package ru.tecius.api.dto.document.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для получения истории изменения документа")
public record DocumentHistoryResponseDto(
    @Schema(description = "Список версий документа", requiredMode = REQUIRED)
    List<DocumentHistoryDataDto> data
) {

}
