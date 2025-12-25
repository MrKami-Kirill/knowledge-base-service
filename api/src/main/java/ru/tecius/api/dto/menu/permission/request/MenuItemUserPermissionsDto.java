package ru.tecius.api.dto.menu.permission.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import ru.tecius.api.dto.common.Permission;
import ru.tecius.api.dto.common.PermissionAction;

@Schema(description = "Данные пользователя и действий с разрешениями")
public record MenuItemUserPermissionsDto(
    @Schema(description = "ID пользователя", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    @NotNull(message = "Поле id не может быть null")
    UUID id,
    @Schema(description = "Тип действия с разрешением", example = "ADD",
        requiredMode = REQUIRED)
    @NotNull(message = "Поле action не может быть null")
    PermissionAction action,
    @Schema(description = "Разрешения", example = "[\"READ\", \"WRITE\", \"DELETE\"]",
        requiredMode = NOT_REQUIRED)
    List<Permission> permissions
) {

}
