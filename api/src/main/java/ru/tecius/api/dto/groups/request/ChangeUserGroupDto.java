package ru.tecius.api.dto.groups.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import ru.tecius.api.dto.common.UserGroupAction;

@Schema(description = "Данные пользователя для изменение группы")
public record ChangeUserGroupDto(
    @Schema(description = "ID пользователя", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
    requiredMode = REQUIRED)
    @NotNull(message = "Поле id не может быть null")
    UUID id,
    @Schema(description = "Тип действия", example = "ADD", requiredMode = REQUIRED)
    @NotNull(message = "Поле action не может быть null")
    UserGroupAction action
) {

}
