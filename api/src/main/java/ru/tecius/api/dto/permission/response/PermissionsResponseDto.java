package ru.tecius.api.dto.permission.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO для получения списка разрешений")
public record PermissionsResponseDto(
    @Schema(description = "Список разрешений", requiredMode = REQUIRED)
    List<PermissionsDataDto> data
) {

}
