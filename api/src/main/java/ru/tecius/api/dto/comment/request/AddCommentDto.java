package ru.tecius.api.dto.comment.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Schema(description = "DTO для создания комментария")
public record AddCommentDto(
    @Schema(description = "ID родительского комментария",
        example = "019b4f74-58e2-7185-9a4b-b82619f12503", requiredMode = NOT_REQUIRED)
    UUID parentId,
    @Schema(description = "Текст комментария", example = "Какой-то комментарий",
        requiredMode = REQUIRED)
    @NotBlank(message = "Поле text не может быть пустым")
    String text
) {

}
