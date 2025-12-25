package ru.tecius.api.dto.menu.user.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для получения списка пользователей элемента меню/документа")
public record MenuItemUsersResponseDto(
    @Schema(description = "Список пользователей элемента меню/документа", requiredMode = REQUIRED)
    List<MenuItemUserDataDto> data
) {

}
