package ru.tecius.api.dto.comment.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(description = "Данные комментария к документу")
public record CommentDataDto(
    @Schema(description = "ID комментария", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID id,
    @Schema(description = "ID родительского комментария", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = NOT_REQUIRED)
    UUID parentId,
    @Schema(description = "Текст комментария", example = "Какой-то комментарий")
    String text,
    @Schema(description = "Автор комментария", example = "Иванов Иван Иванович")
    String author,
    @Schema(description = "Дата комментария с тайм зоной", example = "25-12-2025T10:00:00+03:00ч")
    OffsetDateTime date,
    @Schema(description = "Дочерние комментарии")
    CommentDataDto children) {

}
