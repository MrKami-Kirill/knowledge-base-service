package ru.tecius.api.controller;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tecius.api.dto.document.request.DraftDocumentDto;
import ru.tecius.api.dto.document.request.PublishDocumentDto;
import ru.tecius.api.dto.document.response.DocumentHistoryResponseDto;
import ru.tecius.api.dto.document.response.DocumentResponseDto;
import ru.tecius.api.dto.document.response.DraftDocumentResponseDto;
import ru.tecius.api.dto.document.search.response.FullTextSearchResponseDto;
import ru.tecius.api.dto.document.session.response.SessionResponseDto;
import ru.tecius.api.dto.error.ErrorResponseDto;

@Tag(name = "documents", description = "API для работы с документами")
@RequestMapping("/api/v1/documents")
public interface DocumentsController {

  @Operation(summary = "API Для полнотекстового поиска документов")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Получен список документов"),
          @ApiResponse(responseCode = "500", description = "Ошибка сервера",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponseDto.class)
              ))
      })
  @GetMapping("/full-text-search")
  ResponseEntity<FullTextSearchResponseDto> fullTextSearch(
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
          @ApiResponse(responseCode = "200", description = "Получен pdf файл"),
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

  @Operation(summary = "API Для получения сессии документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Сессия получена"),
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
  @GetMapping("/{documentId}/session")
  ResponseEntity<SessionResponseDto> getSession(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId
  );

  @Operation(summary = "API Для создания сессии документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Сессия документа создана",
              headers = {
                  @Header(name = LOCATION, description = "Новый URL-адрес для запрошенного ресурса",
                      example = "/api/v1/documents/019b4f74-58e2-7185-9a4b-b82619f12503/sessions")
              }),
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
  @PostMapping("/{documentId}/session")
  ResponseEntity<Void> createSession(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId
  );

  @Operation(summary = "API Для удаления сессии документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "Сессия удалена"),
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
  @DeleteMapping("/{documentId}/session")
  ResponseEntity<Void> deleteSession(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId
  );

  @Operation(summary = "API Для создания черновика документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Черновик создан",
              headers = {
                  @Header(name = LOCATION, description = "Новый URL-адрес для запрошенного ресурса",
                      example = "/api/v1/documents/019b4f74-58e2-7185-9a4b-b82619f12503")
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
  @PostMapping("/draft")
  ResponseEntity<DraftDocumentResponseDto> draftDocument(@RequestBody @Valid DraftDocumentDto dto);

  @Operation(summary = "API Для публикации документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Документ опубликован"),
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
  @PostMapping("/{documentId}/publish")
  ResponseEntity<Void> publishDocument(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @ModelAttribute @Valid PublishDocumentDto dto
  );

  @Operation(summary = "API Для удаления документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "Документ удален"),
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
  @DeleteMapping("/{documentId}")
  ResponseEntity<SessionResponseDto> deleteDocument(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId
  );

  @Operation(summary = "API Для получения истории изменений документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "История изменений получена"),
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
  @GetMapping("/{documentId}/history")
  ResponseEntity<DocumentHistoryResponseDto> getDocumentHistory(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId
  );

  @Operation(summary = "API Для получения документа по ID")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Документ получен",
              content = @Content(
                  schema = @Schema(example = """
                      --ZN0QkiMnU6Jm9vDkFEGh_KcPNNNPEPnuEEvHE
                      Content-Disposition: form-data; name="metadata"
                      Content-Type: application/json
                      
                      {"data":{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","title":"Страница_1_1","permissions":["READ","WRITE","DELETE"],"inheritParentPermissions":false,"locked":true,"showHistory":true,"allowComments":true,"version":1,"attachments":[{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","fileName":"Картинка.png","extension":".png"}],"comments":[{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","text":"Тут могла быть ваша реклама 1","author":"Иванов Иван Иванович","date":"2025-12-25T10:00:00+03:00","children":[{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","text":"Тут могла быть ваша реклама 1.1","author":"Иванов Иван Иванович","date":"2025-12-25T10:00:00+03:00","children":null},{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","text":"Тут могла быть ваша реклама 1.2","author":"Иванов Иван Иванович","date":"2025-12-25T10:00:00+03:00","children":null}]},{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","text":"Тут могла быть ваша реклама 2","author":"Иванов Иван Иванович","date":"2025-12-25T10:00:00+03:00","children":null}]}}
                      --ZN0QkiMnU6Jm9vDkFEGh_KcPNNNPEPnuEEvHE
                      Content-Type: text/html
                      Content-disposition: attachment; filename="simple-page.html"
                      Content-Length: 101
                      
                      <!DOCTYPE html>
                      <html>
                      <body>
                      
                      <h1>My First Heading</h1>
                      <p>My first paragraph.</p>
                      
                      </body>
                      </html>
                      
                      --ZN0QkiMnU6Jm9vDkFEGh_KcPNNNPEPnuEEvHE--
                      """,
                      allOf = {
                          DocumentResponseDto.class,
                          ByteArrayResource.class
                      })
              )),
          @ApiResponse(responseCode = "404", description = "Ошибка получения данных",
              content = @Content(
                  schema = @Schema(example = """
                      """, implementation = ErrorResponseDto.class)
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
  @GetMapping(value = "/{documentId}", produces = {
      MULTIPART_FORM_DATA_VALUE,
      APPLICATION_JSON_VALUE
  })
  ResponseEntity<MultiValueMap<String, Object>> getDocumentById(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId
  );

  @Operation(summary = "API Для получения документа по path")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Документ получен",
              content = @Content(
                  schema = @Schema(example = """
                      --ZN0QkiMnU6Jm9vDkFEGh_KcPNNNPEPnuEEvHE
                      Content-Disposition: form-data; name="metadata"
                      Content-Type: application/json
                      
                      {"data":{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","title":"Страница_1_1","permissions":["READ","WRITE","DELETE"],"inheritParentPermissions":false,"locked":true,"showHistory":true,"allowComments":true,"version":1,"attachments":[{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","fileName":"Картинка.png","extension":".png"}],"comments":[{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","text":"Тут могла быть ваша реклама 1","author":"Иванов Иван Иванович","date":"2025-12-25T10:00:00+03:00","children":[{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","text":"Тут могла быть ваша реклама 1.1","author":"Иванов Иван Иванович","date":"2025-12-25T10:00:00+03:00","children":null},{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","text":"Тут могла быть ваша реклама 1.2","author":"Иванов Иван Иванович","date":"2025-12-25T10:00:00+03:00","children":null}]},{"id":"019b4f74-58e2-7185-9a4b-b82619f12503","text":"Тут могла быть ваша реклама 2","author":"Иванов Иван Иванович","date":"2025-12-25T10:00:00+03:00","children":null}]}}
                      --ZN0QkiMnU6Jm9vDkFEGh_KcPNNNPEPnuEEvHE
                      Content-Type: text/html
                      Content-disposition: attachment; filename="simple-page.html"
                      Content-Length: 101
                      
                      <!DOCTYPE html>
                      <html>
                      <body>
                      
                      <h1>My First Heading</h1>
                      <p>My first paragraph.</p>
                      
                      </body>
                      </html>
                      
                      --ZN0QkiMnU6Jm9vDkFEGh_KcPNNNPEPnuEEvHE--
                      """,
                      allOf = {
                          DocumentResponseDto.class,
                          ByteArrayResource.class
                      })
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
  @GetMapping(produces = {
      MULTIPART_FORM_DATA_VALUE,
      APPLICATION_JSON_VALUE
  })
  ResponseEntity<MultiValueMap<String, Object>> getDocumentByPath(
      @Schema(description = "Путь до элемента меню/документа", example = "/stranica_1")
      @RequestParam String path
  );

}
