package ru.tecius.api.dto.comment.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Schema(description = "Данные комментария к документу")
public record CommentDataDto(
    @Schema(description = "ID комментария", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID id,
    @Schema(description = "Текст комментария", example = "Какой-то комментарий",
        requiredMode = REQUIRED)
    String text,
    @Schema(description = "Автор комментария", example = "Иванов Иван Иванович",
        requiredMode = REQUIRED)
    String author,
    @Schema(description = "Дата комментария с тайм зоной", example = "2025-12-25T10:00:00+03:00ч",
        requiredMode = REQUIRED)
    OffsetDateTime date,
    @Schema(description = "Дочерние комментарии",
        requiredMode = NOT_REQUIRED)
    List<CommentDataDto> children) {

}
