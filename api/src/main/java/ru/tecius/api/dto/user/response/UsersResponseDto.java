package ru.tecius.api.dto.user.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для получения списка пользователей")
public record UsersResponseDto(
    @Schema(description = "Список пользователей", requiredMode = REQUIRED)
    List<UserDataDto> data
) {

}
