package ru.tecius.api.controller;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tecius.api.dto.error.ErrorResponseDto;
import ru.tecius.api.dto.menu.permission.request.ChangeMenuItemGroupPermissionsDto;
import ru.tecius.api.dto.menu.permission.request.ChangeMenuItemPermissionsDto;
import ru.tecius.api.dto.menu.permission.request.ChangeMenuItemUserPermissionsDto;
import ru.tecius.api.dto.menu.user.response.MenuItemUsersResponseDto;

@Tag(name = "menu", description = "API для работы с меню")
@RequestMapping("/api/v1/menu")
public interface MenuController {

  @Operation(summary = "API Для получения списка пользователей элемента меню/документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Получен список пользователей"),
          @ApiResponse(responseCode = "404", description = "Ошибка получения данных",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "403", description = "Ошибка доступа",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @GetMapping("/{menuItemId}/users")
  ResponseEntity<MenuItemUsersResponseDto> getMenuItemUsers(
      @Schema(description = "ID элемента меню", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID menuItemId
  );

  @Operation(summary = "API Для получения списка пользователей элемента меню/документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Получен список групп пользователей для элемента меню/документа"),
          @ApiResponse(responseCode = "404", description = "Ошибка получения данных",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "403", description = "Ошибка доступа",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @GetMapping("/{menuItemId}/groups")
  ResponseEntity<MenuItemUsersResponseDto> getMenuItemGroups(
      @Schema(description = "ID элемента меню", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID menuItemId
  );

  @Operation(summary = "API Добавление/удаление пользователей у элемента меню")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Изменен список разрешений для пользователей у элемента меню/документа"),
          @ApiResponse(responseCode = "404", description = "Ошибка получения данных",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "403", description = "Ошибка доступа",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @PostMapping("/{menuItemId}/permissions/users")
  ResponseEntity<Void> changeMenuItemUserPermissions(
      @Schema(description = "ID элемента меню", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID menuItemId,
      @RequestBody @Valid ChangeMenuItemUserPermissionsDto dto
  );

  @Operation(summary = "API Добавление/удаление групп пользователей у элемента меню")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Изменен список разрешений для групп пользователей у элемента меню/документа"),
          @ApiResponse(responseCode = "404", description = "Ошибка получения данных",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "403", description = "Ошибка доступа",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @PostMapping("/{menuItemId}/permissions/groups")
  ResponseEntity<Void> changeMenuItemGroupPermissions(
      @Schema(description = "ID элемента меню", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID menuItemId,
      @RequestBody @Valid ChangeMenuItemGroupPermissionsDto dto
  );

  @Operation(summary = "API Изменение разрешений у элемента меню/документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Изменен список разрешений для групп пользователей у элемента меню/документа"),
          @ApiResponse(responseCode = "404", description = "Ошибка получения данных",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "403", description = "Ошибка доступа",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @PatchMapping("/{menuItemId}/permissions")
  ResponseEntity<Void> changeMenuItemPermissions(
      @Schema(description = "ID элемента меню", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID menuItemId,
      @Schema(description = "DTO для изменения разрешений у элемента меню/документа",
          example = """
              {
                "inheritParentPermissions": true,
                "allowComments": true,
                "showHistory": true,
                "groups": [
                  {
                    "id": "019b4f72-9605-7183-8b7b-1046adfd3847",
                    "action": "ADD",
                    "permissions": [
                      "READ",
                      "WRITE",
                      "DELETE"
                    ]
                  },
                  {
                    "id": "019b4f72-9605-7183-8b7b-1046adfd3847",
                    "action": "DELETE"
                  }
                ],
                "users": [
                  {
                    "id": "019b4f72-9605-7183-8b7b-1046adfd3847",
                    "action": "ADD",
                    "permissions": [
                      "READ",
                      "WRITE",
                      "DELETE"
                    ]
                  },
                  {
                    "id": "019b4f72-9605-7183-8b7b-1046adfd3847",
                    "action": "DELETE"
                  }
                ]
              }
              """,
          requiredMode = REQUIRED,
          implementation = ChangeMenuItemPermissionsDto.class)
      @RequestBody Map<String, Object> dto
  );

}
