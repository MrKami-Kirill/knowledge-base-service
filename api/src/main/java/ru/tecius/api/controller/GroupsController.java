package ru.tecius.api.controller;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import static org.springframework.http.HttpHeaders.LOCATION;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tecius.api.dto.error.ErrorResponseDto;
import ru.tecius.api.dto.groups.request.AddGroupDto;
import ru.tecius.api.dto.groups.request.ChangeGroupDto;
import ru.tecius.api.dto.groups.response.GroupDataDto;
import ru.tecius.api.dto.groups.response.GroupsResponseDto;

@Tag(name = "groups", description = "API для работы с группами пользователей")
@RequestMapping("/api/v1/groups")
public interface GroupsController {

  @Operation(summary = "API Для получения списка групп пользователей")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Список групп пользователей получен"),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @GetMapping
  ResponseEntity<GroupsResponseDto> getGroups();

  @Operation(summary = "API Для получения группы пользователей по ID")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Группа пользователей получена"),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @GetMapping("/{groupId}")
  ResponseEntity<GroupDataDto> getGroupById(
      @Schema(description = "ID группы пользователей",
          example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID groupId
  );

  @Operation(summary = "API Для создания группы пользователей")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Группа пользователей создана",
              headers = {
                  @Header(name = LOCATION, description = "Новый URL-адрес для запрошенного ресурса",
                      example = "/api/v1/groups/019b4f74-58e2-7185-9a4b-b82619f12503")
              }),
          @ApiResponse(responseCode = "400", description = "Ошибка обработки запроса",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
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
  @PostMapping
  ResponseEntity<Void> addGroup(@RequestBody @Valid AddGroupDto dto);

  @Operation(summary = "API Для удаления группы пользователей")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "Группа пользователей удалена"),
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
  @DeleteMapping("/{groupId}")
  ResponseEntity<Void> deleteGroup(
      @Schema(description = "ID группы пользователей",
          example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID groupId
  );

  @Operation(summary = "API Для изменения группы пользователей")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "Группа пользователей изменена"),
          @ApiResponse(responseCode = "400", description = "Ошибка обработки запроса",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              )),
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
  @PatchMapping(value = "/{groupId}")
  ResponseEntity<Void> changeGroup(
      @Schema(description = "ID группы пользователей",
          example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID groupId,
      @Schema(description = "DTO для изменения группы пользователей",
          example = """
              {
                "title": "Группа 2",
                "isActive": "false",
                "users": [
                  {
                    "id": "019b4f74-58e2-7185-9a4b-b82619f12503",
                    "action": "DELETE"
                  },
                  {
                    "id": "019b4f74-58e2-7185-9a4b-b82619f12504",
                    "action": "ADD"
                  },
                  {
                    "id": "019b4f74-58e2-7185-9a4b-b82619f12505",
                    "action": "ADD"
                  }
                ]
              }
              """,
          requiredMode = REQUIRED,
          implementation = ChangeGroupDto.class)
      @RequestBody Map<String, Object> dto
  );

}
