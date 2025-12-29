package ru.tecius.api.controller;

import static org.springframework.http.HttpHeaders.LOCATION;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tecius.api.dto.attachment.request.AddAttachmentDto;
import ru.tecius.api.dto.attachment.response.AttachmentsResponseDto;
import ru.tecius.api.dto.error.ErrorResponseDto;

@Tag(name = "documentAttachments", description = "API для работы с вложениями документа")
@RequestMapping("/api/v1/documents/{documentId}/attachments")
public interface AttachmentsController {

  @Operation(summary = "API Для получения списка вложений документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Список вложений получен"),
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
  ResponseEntity<AttachmentsResponseDto> getAttachments(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId
  );

  @Operation(summary = "API Для получения файла вложения документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Файл вложения получен"),
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
  @GetMapping("/{attachmentId}")
  ResponseEntity<ByteArrayResource> getAttachment(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @Schema(description = "ID вложения", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID attachmentId
  );

  @Operation(summary = "API Для добавления файла вложения к документу")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Файл вложения добавлен",
              headers = {
                  @Header(name = LOCATION, description = "Новый URL-адрес для запрошенного ресурса",
                      example = "/api/v1/documents/019b4f74-58e2-7185-9a4b-b82619f12503/attachments/019b4f74-58e2-7185-9a4b-b82619f12503")
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
  @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
  ResponseEntity<Void> addAttachment(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @Schema(description = "DTO для добавления файла вложения к документу", implementation = AddAttachmentDto.class)
      @ModelAttribute @Valid AddAttachmentDto dto
  );

  @Operation(summary = "API Для удаления файла вложения у документа")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "Файл вложения удален"),
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
  @DeleteMapping("/{attachmentId}")
  ResponseEntity<Void> deleteAttachment(
      @Schema(description = "ID документа", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID documentId,
      @Schema(description = "ID вложения", example = "019b4f74-58e2-7185-9a4b-b82619f12503")
      @PathVariable UUID attachmentId
  );



}
