package ru.tecius.api.dto.menu.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import ru.tecius.api.dto.common.Permission;

@Schema(description = "Данные элемента меню")
public record MenuItemDataDto(
    @Schema(description = "Название элемента меню", example = "Страница 1",
        requiredMode = REQUIRED)
    String title,
    @Schema(description = "Есть ли дочерние элементы у меню", example = "true",
        requiredMode = REQUIRED)
    Boolean hasChildren,
    @Schema(description = "Путь до элемента меню", example = "/stranica_1",
        requiredMode = REQUIRED)
    String path,
    @Schema(description = "ID элемента меню", example = "019b4f74-58e2-7185-9a4b-b82619f12503",
        requiredMode = REQUIRED)
    UUID uuid,
    @Schema(description = "Разрешения", example = "[\"READ\", \"WRITE\", \"DELETE\"]",
        requiredMode = REQUIRED)
    List<Permission> permissions,
    @Schema(description = "Список дочерних элементов меню", example = "Страница 1",
        requiredMode = NOT_REQUIRED)
    List<MenuTreeDataDto> children
) {

}
