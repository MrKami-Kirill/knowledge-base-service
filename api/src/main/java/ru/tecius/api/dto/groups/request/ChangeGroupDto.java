package ru.tecius.api.dto.groups.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;

@Schema(description = "DTO для изменения группы пользователей")
public record ChangeGroupDto(
    @Schema(description = "Название группы", example = "Группа 2", requiredMode = NOT_REQUIRED)
    String title,
    @Schema(description = "Признак активности группы", example = "true",
        requiredMode = NOT_REQUIRED)
    Boolean isActive,
    @Schema(description = "Список пользователей для изменения группы", requiredMode = NOT_REQUIRED)
    @Valid
    List<ChangeUserGroupDto> users) {

}
