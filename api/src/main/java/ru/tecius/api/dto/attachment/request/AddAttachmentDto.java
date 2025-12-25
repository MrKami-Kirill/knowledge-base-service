package ru.tecius.api.dto.attachment.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

@Schema(description = "DTO для добавления файла вложения к документу")
public record AddAttachmentDto(
    @Schema(description = "Название файла", example = "Картинка.png", requiredMode = REQUIRED)
    @NotBlank(message = "Поле name не может быть пустым")
    String fileName,
    @Schema(description = "Расширение файла вложения", example = ".png", requiredMode = REQUIRED)
    @NotBlank(message = "Поле extension не может быть пустым")
    String extension,
    @Schema(description = "Файл вложения", requiredMode = REQUIRED)
    @NotNull(message = "Поле file не может быть null")
    MultipartFile file
) {

}
