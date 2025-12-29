package ru.tecius.api.dto.document.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Schema(description = "DTO для создания черновика документа")
public record DraftDocumentDto(
    @Schema(description = "Название элемента меню/документа", example = "Новая страница",
        requiredMode = REQUIRED)
    @NotBlank(message = "Поле title не может быть пустым")
    String title,
    @Schema(description = "ID родительского элемента меню/документа",
        example = "019b4f72-9605-7183-8b7b-1046adfd3847", requiredMode = NOT_REQUIRED)
    UUID parentId
) {

}
