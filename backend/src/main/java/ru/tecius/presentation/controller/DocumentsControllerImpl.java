package ru.tecius.presentation.controller;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.TEXT_HTML;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import ru.tecius.api.controller.DocumentsController;
import ru.tecius.api.dto.attachment.response.AttachmentDataDto;
import ru.tecius.api.dto.comment.response.CommentDataDto;
import ru.tecius.api.dto.common.Permission;
import ru.tecius.api.dto.document.request.DraftDocumentDto;
import ru.tecius.api.dto.document.request.PublishDocumentDto;
import ru.tecius.api.dto.document.response.DocumentDataDto;
import ru.tecius.api.dto.document.response.DocumentHistoryResponseDto;
import ru.tecius.api.dto.document.response.DocumentResponseDto;
import ru.tecius.api.dto.document.response.DraftDocumentResponseDto;
import ru.tecius.api.dto.document.search.response.FullTextSearchResponseDto;
import ru.tecius.api.dto.document.session.response.SessionResponseDto;

@RestController
@RequiredArgsConstructor
public class DocumentsControllerImpl implements DocumentsController {

  private final ObjectMapper objectMapper;

  @Override
  public ResponseEntity<FullTextSearchResponseDto> fullTextSearch(String query, Integer limit,
      Integer offset) {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<ByteArrayResource> print(UUID documentId) {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<SessionResponseDto> getSession(UUID documentId) {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<Void> createSession(UUID documentId) {
    return ResponseEntity.status(CREATED).build();
  }

  @Override
  public ResponseEntity<Void> deleteSession(UUID documentId) {
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<DraftDocumentResponseDto> draftDocument(DraftDocumentDto dto) {
    return ResponseEntity.status(CREATED).build();
  }

  @Override
  public ResponseEntity<Void> publishDocument(UUID documentId, PublishDocumentDto dto) {
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<SessionResponseDto> deleteDocument(UUID documentId) {
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<DocumentHistoryResponseDto> getDocumentHistory(UUID documentId) {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<MultiValueMap<String, Object>> getDocumentById(UUID documentId) {
    try {
      var body = new LinkedMultiValueMap<String, Object>();
      var jsonHeaders = new HttpHeaders();
      jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
      var jsonPayload = new DocumentResponseDto(
          new DocumentDataDto(UUID.fromString("019b4f74-58e2-7185-9a4b-b82619f12503"),
              "Страница_1_1",
              List.of(Permission.READ, Permission.WRITE, Permission.DELETE),
              false,
              true,
              true,
              true,
              1,
              List.of(
                  new AttachmentDataDto(
                      UUID.fromString("019b4f74-58e2-7185-9a4b-b82619f12503"),
                      "Картинка.png",
                      ".png"
                  )
              ),
              List.of(objectMapper.readValue("""
                      {
                        "id": "019b4f74-58e2-7185-9a4b-b82619f12503",
                        "parentId": "019b4f74-58e2-7185-9a4b-b82619f12503",
                        "text": "Тут могла быть ваша реклама 1",
                        "author": "Иванов Иван Иванович",
                        "date": "2025-12-25T10:00:00+03:00",
                        "children": [
                          {
                            "id": "019b4f74-58e2-7185-9a4b-b82619f12503",
                            "text": "Тут могла быть ваша реклама 1.1",
                            "author": "Иванов Иван Иванович",
                            "date": "2025-12-25T10:00:00+03:00"
                          },
                          {
                            "id": "019b4f74-58e2-7185-9a4b-b82619f12503",
                            "text": "Тут могла быть ваша реклама 1.2",
                            "author": "Иванов Иван Иванович",
                            "date": "2025-12-25T10:00:00+03:00"
                          }
                        ]
                      }""", CommentDataDto.class),
                  objectMapper.readValue("""
                      {
                        "id": "019b4f74-58e2-7185-9a4b-b82619f12503",
                        "text": "Тут могла быть ваша реклама 2",
                        "author": "Иванов Иван Иванович",
                        "date": "2025-12-25T10:00:00+03:00"
                      }""", CommentDataDto.class)))
      );

      var jsonPart = new HttpEntity<>(jsonPayload, jsonHeaders);
      body.add("metadata", jsonPart);

      var fileHeaders = new HttpHeaders();
      fileHeaders.setContentType(TEXT_HTML);
      fileHeaders.add(CONTENT_DISPOSITION, "attachment; filename=\"simple-page.html\"");
      var fileResource = new ByteArrayResource("""
          <!DOCTYPE html>
          <html>
          <body>
          
          <h1>My First Heading</h1>
          <p>My first paragraph.</p>
          
          </body>
          </html>
          """.getBytes()) {
        @Override
        public String getFilename() {
          return "simple-page.html";
        }
      };
      var filePart = new HttpEntity<>(fileResource, fileHeaders);
      body.add("file", filePart);

      return ResponseEntity.ok()
          .contentType(MediaType.MULTIPART_FORM_DATA)
          .body(body);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<MultiValueMap<String, Object>> getDocumentByPath(String path) {
    return ResponseEntity.status(OK).build();
  }
}
