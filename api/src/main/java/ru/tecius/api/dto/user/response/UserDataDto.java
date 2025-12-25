package ru.tecius.api.dto.user.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Данные пользователя")
public record UserDataDto(
    @Schema(description = "ID пользователя", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID id,
    @Schema(description = "Имя пользователя/псевдоним", example = "Иванов Иван Иванович",
        requiredMode = REQUIRED)
    String name
) {

}
