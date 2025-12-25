package ru.tecius.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tecius.api.dto.document.search.response.FullTextSearchResponseDto;
import ru.tecius.api.dto.error.ErrorResponseDto;

@Tag(name = "documents", description = "API для работы с документами")
@RequestMapping("/api/v1/documents")
public interface DocumentsController {

  @Operation(summary = "Полнотекстовый поиск документов")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Получен список комментариев документа"),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @GetMapping
  ResponseEntity<FullTextSearchResponseDto> getComments(
      @Schema(description = "Строка запроса", example = "Документ 1")
      @RequestParam String query,
      @Schema(description = "Количество возвращаемых записей", example = "100")
      @RequestParam(required = false) Integer limit,
      @Schema(description = "Сдвиг, указывающий, с какой записи начать возврат",
          example = "0")
      @RequestParam(required = false) Integer offset
  );

  @Operation(summary = "API Для скачивания документа в формате PDF")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Получен pdf файл документа"),
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
  @GetMapping("/{documentId}/print")
  ResponseEntity<ByteArrayResource> print(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId
  );

}
