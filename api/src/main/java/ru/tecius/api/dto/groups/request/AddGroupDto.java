package ru.tecius.api.dto.groups.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Schema(description = "DTO для создания группы пользователей")
public record AddGroupDto(
    @Schema(description = "Название группы", example = "Группа 1", requiredMode = REQUIRED)
    @NotBlank(message = "Поле title не может быть пустым")
    String title,
    @Schema(description = "Признак активности группы", example = "true", requiredMode = REQUIRED)
    @NotNull(message = "Поле isActive не может быть null")
    Boolean isActive,
    @Schema(description = "Список ID пользователей группы",
        example = """
            [
               "019b4f74-58e2-7185-9a4b-b82619f12503",
               "019b4f74-58e2-7185-9a4b-b82619f12504",
               "019b4f74-58e2-7185-9a4b-b82619f12505"
            ]
            """,
        requiredMode = NOT_REQUIRED)
    List<UUID> users) {

}
