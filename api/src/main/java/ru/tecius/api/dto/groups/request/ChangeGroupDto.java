package ru.tecius.api.dto.groups.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;

@Schema(description = "DTO для изменения группы пользователей")
public record ChangeGroupDto(
    @Schema(description = "Название группы", example = "Группа 2")
    String title,
    @Schema(description = "Признак активности группы", example = "true")
    Boolean isActive,
    @Schema(description = "Список пользователей для изменения группы")
    @Valid
    List<ChangeUserGroupDto> users) {

}
