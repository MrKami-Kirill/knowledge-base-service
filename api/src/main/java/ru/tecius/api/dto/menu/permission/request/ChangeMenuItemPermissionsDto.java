package ru.tecius.api.dto.menu.permission.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import java.util.List;

@Schema(description = "DTO для изменения разрешений у элемента меню/документа")
public record ChangeMenuItemPermissionsDto(
    @Schema(description = "Признак наследования разрешений от родителя", example = "true",
        requiredMode = RequiredMode.NOT_REQUIRED)
    Boolean inheritParentPermissions,
    @Schema(description = "Признак, который разрешает/запрещает комментировать документ",
        example = "true",
        requiredMode = RequiredMode.NOT_REQUIRED)
    Boolean allowComments,
    @Schema(description = "Признак, который разрешает/запрещает показывать историю документа",
        example = "true",
        requiredMode = RequiredMode.NOT_REQUIRED)
    Boolean showHistory,
    @Schema(description = "Список пользователей и разрешений", requiredMode = NOT_REQUIRED)
    @Valid
    List<MenuItemUserPermissionsDto> users,
    @Schema(description = "Список групп пользователей и разрешений", requiredMode = NOT_REQUIRED)
    @Valid
    List<MenuItemGroupPermissionsDto> groups

) {

}
