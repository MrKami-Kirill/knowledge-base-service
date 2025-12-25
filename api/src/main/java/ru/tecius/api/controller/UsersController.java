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
import ru.tecius.api.dto.user.response.UsersResponseDto;

@Tag(name = "users", description = "API для работы с пользователями")
@RequestMapping("/api/v1")
public interface UsersController {

  @Operation(summary = "API Для получения списка пользователей")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Получен список пользователей"),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @GetMapping("/users")
  ResponseEntity<UsersResponseDto> getUsers();

}
