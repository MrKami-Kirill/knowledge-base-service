package ru.tecius.api.dto.attachment.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Данные вложения, приложенного к документу")
public record AttachmentDataDto(
    @Schema(description = "ID вложения", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID id,
    @Schema(description = "Название файла", example = "Картинка.png", requiredMode = REQUIRED)
    String fileName,
    @Schema(description = "Расширение файла вложения", example = ".png", requiredMode = REQUIRED)
    String extension
) {

}
