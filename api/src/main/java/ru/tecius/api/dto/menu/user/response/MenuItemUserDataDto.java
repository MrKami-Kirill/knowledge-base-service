package ru.tecius.api.dto.menu.user.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import ru.tecius.api.dto.common.Permission;

@Schema(description = "Данные пользователей элемента меню/документа")
public record MenuItemUserDataDto(
    @Schema(description = "ID пользователя", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID id,
    @Schema(description = "Имя пользователя/псевдоним", example = "Иванов Иван Иванович",
        requiredMode = REQUIRED)
    String name,
    @Schema(description = "Разрешения", example = "[\"READ\", \"WRITE\", \"DELETE\"]",
        requiredMode = NOT_REQUIRED)
    List<Permission> permissions
) {

}
