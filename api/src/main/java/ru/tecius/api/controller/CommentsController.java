package ru.tecius.api.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.tecius.api.dto.comment.request.AddCommentDto;
import ru.tecius.api.dto.comment.request.ChangeCommentTextDto;
import ru.tecius.api.dto.comment.response.CommentsResponseDto;
import ru.tecius.api.dto.error.ErrorResponseDto;

@Tag(name = "documentComments", description = "API для работы с комментариями документа")
@RequestMapping("/api/v1/documents/{documentId}/comments")
public interface CommentsController {

  @Operation(summary = "API Получение комментариев документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Получен список комментариев документа"),
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
  @GetMapping
  ResponseEntity<CommentsResponseDto> getComments(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId
  );


  @Operation(summary = "API Добавление комментария к документу")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Комментарий к документу создан"),
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
  ResponseEntity<Void> addComment(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @RequestBody @Valid AddCommentDto dto
  );

  @Operation(summary = "API Удаление комментария документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "Комментарий удален у документа"),
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
  @DeleteMapping("/{commentId}")
  @ResponseStatus(NO_CONTENT)
  ResponseEntity<Void> deleteComment(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @Schema(description = "ID комментария", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID commentId
  );

  @Operation(summary = "API Изменение текста комментария документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Текст комментария к документу изменен"),
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
  @PostMapping("/{commentId}")
  @ResponseStatus(NO_CONTENT)
  ResponseEntity<Void> changeCommentText(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @Schema(description = "ID комментария", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID commentId,
      @RequestBody @Valid ChangeCommentTextDto dto
  );

}
