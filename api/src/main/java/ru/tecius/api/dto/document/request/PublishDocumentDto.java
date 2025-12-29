package ru.tecius.api.dto.document.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

@Schema(description = "DTO для публикации документа")
public record PublishDocumentDto(
    @Schema(description = "Название элемента меню/документа", example = "Опубликованная страница",
        requiredMode = REQUIRED)
    @NotBlank(message = "Поле title не может быть пустым")
    String title,
    @Schema(description = "HTML контент документа", requiredMode = REQUIRED)
    @NotNull(message = "Поле file не может быть null")
    MultipartFile file
) {

}
