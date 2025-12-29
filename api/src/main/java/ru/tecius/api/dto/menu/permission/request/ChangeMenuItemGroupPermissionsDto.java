package ru.tecius.api.dto.menu.permission.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "DTO добавления/удаления разрешений групп пользователей у элемента меню")
public record ChangeMenuItemGroupPermissionsDto(
    @Schema(description = "Список групп пользователей и разрешений", requiredMode = REQUIRED)
    @NotNull(message = "Поле data не может быть null")
    @NotEmpty(message = "Поле data не может быть пустым")
    List<MenuItemGroupPermissionsDto> data
) {

}
