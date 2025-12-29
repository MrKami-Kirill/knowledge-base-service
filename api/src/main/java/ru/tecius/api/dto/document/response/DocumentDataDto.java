package ru.tecius.api.dto.document.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import ru.tecius.api.dto.attachment.response.AttachmentDataDto;
import ru.tecius.api.dto.comment.response.CommentDataDto;
import ru.tecius.api.dto.common.Permission;

@Schema(description = "Данные документа")
public record DocumentDataDto(
    @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID id,
    @Schema(description = "Название элемента меню/документа", example = "Страница 1",
        requiredMode = REQUIRED)
    String title,
    @Schema(description = "Разрешения", example = "[\"READ\", \"WRITE\", \"DELETE\"]",
        requiredMode = NOT_REQUIRED)
    List<Permission> permissions,
    @Schema(description = "Признак наследования разрешений от родителя", example = "true",
        requiredMode = REQUIRED)
    Boolean inheritParentPermissions,
    @Schema(description = "ID комментария", example = "true", requiredMode = REQUIRED)
    Boolean locked,
    @Schema(description = "ID комментария", example = "true", requiredMode = REQUIRED)
    Boolean showHistory,
    @Schema(description = "Признак, который разрешает/запрещает комментировать документ",
        example = "true", requiredMode = REQUIRED)
    Boolean allowComments,
    @Schema(description = "Версия документа", example = "1", requiredMode = REQUIRED)
    Integer version,
    @Schema(description = "Вложения документа", requiredMode = NOT_REQUIRED)
    List<AttachmentDataDto> attachments,
    @Schema(description = "Комментарии к документу", requiredMode = NOT_REQUIRED)
    List<CommentDataDto> comments
) {

}
