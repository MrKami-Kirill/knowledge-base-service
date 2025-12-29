package ru.tecius.api.dto.menu.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Schema(description = "DTO для изменения структуры дерева элементов меню")
public record MoveMenuItemDto(
    @Schema(description = "ID родительского элемента меню",
        example = "019b4f72-9605-7183-8b7b-1046adfd3847", requiredMode = NOT_REQUIRED)
    @NotBlank(message = "Поле parentId не может быть null")
    UUID parentId
) {

}
