package ru.tecius.presentation.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tecius.api.controller.AttachmentsController;
import ru.tecius.api.dto.attachment.request.AddAttachmentDto;
import ru.tecius.api.dto.attachment.response.AttachmentsResponseDto;

@RestController
@RequiredArgsConstructor
public class AttachmentsControllerImpl implements AttachmentsController {

  @Override
  public ResponseEntity<AttachmentsResponseDto> getAttachments(UUID documentId) {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<ByteArrayResource> getAttachment(UUID documentId, UUID attachmentId) {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<Void> addAttachment(UUID documentId, AddAttachmentDto dto) {
    return ResponseEntity.status(CREATED).build();
  }

  @Override
  public ResponseEntity<Void> deleteAttachment(UUID documentId, UUID attachmentId) {
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
