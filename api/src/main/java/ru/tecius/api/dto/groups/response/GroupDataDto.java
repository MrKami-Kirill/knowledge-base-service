package ru.tecius.api.dto.groups.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import ru.tecius.api.dto.user.response.UserDataDto;

@Schema(description = "Данные группы пользователей")
public record GroupDataDto(
    @Schema(description = "ID группы пользователей", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
    requiredMode = REQUIRED)
    UUID id,
    @Schema(description = "Название группы", example = "Группа 1", requiredMode = REQUIRED)
    String title,
    @Schema(description = "Признак активности группы", example = "true", requiredMode = REQUIRED)
    Boolean isActive,
    @Schema(description = "Список пользователей группы", requiredMode = NOT_REQUIRED)
    List<UserDataDto> users) {

}
