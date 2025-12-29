package ru.tecius.api.controller;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.tecius.api.dto.comment.request.AddCommentDto;
import ru.tecius.api.dto.comment.request.ChangeCommentTextDto;
import ru.tecius.api.dto.comment.response.CommentDataDto;
import ru.tecius.api.dto.comment.response.CommentsResponseDto;
import ru.tecius.api.dto.error.ErrorResponseDto;

@Tag(name = "documentComments", description = "API для работы с комментариями документа")
@RequestMapping("/api/v1/documents/{documentId}/comments")
public interface CommentsController {

  @Operation(summary = "API Для получения комментариев документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Список комментариев получен"),
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

  @Operation(summary = "API Для получения комментария документа по ID")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Комментарий получен"),
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
  @GetMapping("/{commentId}")
  ResponseEntity<CommentDataDto> getCommentById(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @Schema(description = "ID комментария", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID commentId
  );


  @Operation(summary = "API Для добавления комментария к документу")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Комментарий добавлен",
              headers = {
                  @Header(name = LOCATION, description = "Новый URL-адрес для запрошенного ресурса",
                      example = "/api/v1/documents/019b4f74-58e2-7185-9a4b-b82619f12503/comments/019b4f74-58e2-7185-9a4b-b82619f12503")
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
  ResponseEntity<Void> addComment(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @RequestBody @Valid AddCommentDto dto
  );

  @Operation(summary = "API Для удаления комментария у документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "Комментарий удален"),
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

  @Operation(summary = "API Для изменения текста комментария у документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "Текст комментария изменен"),
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
  @PatchMapping("/{commentId}")
  @ResponseStatus(NO_CONTENT)
  ResponseEntity<Void> changeCommentText(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @Schema(description = "ID комментария", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID commentId,
      @Schema(description = "DTO для текста комментария документа",
          example = """
              {
                "text": "Какой-то комментарий"
              }
              """,
          requiredMode = REQUIRED,
          implementation = ChangeCommentTextDto.class)
      @RequestBody @Valid Map<String, Object> dto
  );

}
