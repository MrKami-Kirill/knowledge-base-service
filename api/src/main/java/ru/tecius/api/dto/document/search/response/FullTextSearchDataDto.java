package ru.tecius.api.dto.document.search.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Данные документа")
public record FullTextSearchDataDto(
    @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID documentId,
    @Schema(description = "ID элемента меню", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID menuItemId,
    @Schema(description = "Путь до элемента меню/документа", example = "/stranica_1",
        requiredMode = REQUIRED)
    String path,
    @Schema(description = "Название элемента меню/документа", example = "/stranica_1",
        requiredMode = REQUIRED)
    String title,
    @Schema(description = "Обрезанный контент документа", example = "Тут могла быть ваша реклама...",
        requiredMode = NOT_REQUIRED)
    String shortContent
) {

}
