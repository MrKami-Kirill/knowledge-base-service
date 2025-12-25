package ru.tecius.api.dto.attachment.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для отображения списка вложений документа")
public record AttachmentsResponseDto(
    @Schema(description = "Список вложений документа", requiredMode = REQUIRED)
    List<AttachmentDataDto> data
) {

}
