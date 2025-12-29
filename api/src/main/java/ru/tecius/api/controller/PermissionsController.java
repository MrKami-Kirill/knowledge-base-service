package ru.tecius.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tecius.api.dto.error.ErrorResponseDto;
import ru.tecius.api.dto.permission.response.PermissionsResponseDto;

@Tag(name = "permissions", description = "API для работы со справочником разрешений")
@RequestMapping("/api/v1/permissions")
public interface PermissionsController {

  @Operation(summary = "API Для получения списка разрешений (справочник)")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Список разрешений получен"),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @GetMapping
  ResponseEntity<PermissionsResponseDto> getPermissions();

}
