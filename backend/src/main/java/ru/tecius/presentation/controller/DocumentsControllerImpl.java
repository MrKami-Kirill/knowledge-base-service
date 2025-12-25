package ru.tecius.presentation.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tecius.api.controller.DocumentsController;
import ru.tecius.api.dto.document.search.response.FullTextSearchResponseDto;

@RestController
@RequiredArgsConstructor
public class DocumentsControllerImpl implements DocumentsController {

  @Override
  public ResponseEntity<FullTextSearchResponseDto> getComments(String query, Integer limit,
      Integer offset) {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<ByteArrayResource> print(UUID documentId) {
    return ResponseEntity.status(OK).build();
  }
}
