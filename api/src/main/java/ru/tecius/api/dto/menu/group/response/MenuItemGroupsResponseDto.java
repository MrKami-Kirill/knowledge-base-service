package ru.tecius.api.dto.menu.group.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для получения списка групп пользователей элемента меню/документа")
public record MenuItemGroupsResponseDto(
    @Schema(description = "Список групп пользователей элемента меню/документа",
        requiredMode = REQUIRED)
    List<MenuItemGroupDataDto> data
) {

}
