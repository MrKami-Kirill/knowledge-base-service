package ru.tecius.api.dto.groups.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для получения списка групп пользователей")
public record GroupsResponseDto(
    @Schema(description = "Список групп пользователей", requiredMode = REQUIRED)
    List<GroupDataDto> data
) {

}
