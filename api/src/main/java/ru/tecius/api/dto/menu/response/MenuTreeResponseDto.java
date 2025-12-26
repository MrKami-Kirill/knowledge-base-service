package ru.tecius.api.dto.menu.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для получения дерева элементов меню")
public record MenuTreeResponseDto(
    @Schema(description = "Список элементов меню", requiredMode = REQUIRED)
    List<MenuTreeDataDto> data
) {

}
