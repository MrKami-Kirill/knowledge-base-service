package ru.tecius.api.dto.document.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "DTO для получения ответа после создания черновика документа")
public record DraftDocumentResponseDto(
    @Schema(description = "ID элемента меню", example = "019b4f72-9605-7183-8b7b-1046adfd3847",
        requiredMode = REQUIRED)
    UUID menuItemId,
    @Schema(description = "ID документа", example = "019b4f72-9605-7183-8b7b-1046adfd3847",
        requiredMode = REQUIRED)
    UUID documentId,
    @Schema(description = "Путь до элемента меню/документа",
        example = "/stranica_1/novaya_stranica_1", requiredMode = REQUIRED)
    String path
) {

}
