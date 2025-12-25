package ru.tecius.api.dto.comment.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO для изменения текста комментария")
public record ChangeCommentTextDto(
    @Schema(description = "Текст комментария", example = "Какой-то комментарий",
        requiredMode = REQUIRED)
    @NotBlank(message = "Поле text не может быть пустым")
    String text) {

}
