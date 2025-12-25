package ru.tecius.api.dto.document.search.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для получения списка документов")
public record FullTextSearchResponseDto(
    @Schema(description = "Список документов", requiredMode = REQUIRED)
    List<FullTextSearchDataDto> data
) {

}
