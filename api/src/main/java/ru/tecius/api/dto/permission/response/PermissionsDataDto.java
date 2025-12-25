package ru.tecius.api.dto.permission.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import ru.tecius.api.dto.common.Permission;

@Schema(description = "Данные разрешения")
public record PermissionsDataDto(
    @Schema(description = "ID разрешения", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID uuid,
    @Schema(description = "Код разрешения", example = "READ",
        requiredMode = REQUIRED)
    Permission code,
    @Schema(description = "Название разрешения", example = "Разрешение на просмотр документа",
        requiredMode = REQUIRED)
    String title
) {

}
