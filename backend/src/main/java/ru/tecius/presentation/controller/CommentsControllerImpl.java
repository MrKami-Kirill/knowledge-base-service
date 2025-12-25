package ru.tecius.presentation.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tecius.api.controller.CommentsController;
import ru.tecius.api.dto.comment.request.AddCommentDto;
import ru.tecius.api.dto.comment.request.ChangeCommentTextDto;
import ru.tecius.api.dto.comment.response.CommentsResponseDto;

@RestController
@RequiredArgsConstructor
public class CommentsControllerImpl implements CommentsController {

  @Override
  public ResponseEntity<CommentsResponseDto> getComments(UUID documentId) {
    return ResponseEntity.status(OK).build();
}

  @Override
  public ResponseEntity<Void> addComment(UUID documentId, AddCommentDto dto) {
    return ResponseEntity.status(CREATED).build();
  }

  @Override
  public ResponseEntity<Void> deleteComment(UUID documentId, UUID commentId) {
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Void> changeCommentText(UUID documentId, UUID commentId,
      ChangeCommentTextDto dto) {
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
