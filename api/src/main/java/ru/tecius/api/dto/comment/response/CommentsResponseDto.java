package ru.tecius.api.dto.comment.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для отображения списка комментариев к документу")
public record CommentsResponseDto(
    @Schema(description = "Список комментариев документа", requiredMode = REQUIRED)
    List<CommentDataDto> data
) {

}
