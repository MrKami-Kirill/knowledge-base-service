package ru.tecius.presentation.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tecius.api.controller.GroupsController;
import ru.tecius.api.dto.groups.request.AddGroupDto;
import ru.tecius.api.dto.groups.response.GroupsResponseDto;

@RestController
@RequiredArgsConstructor
public class GroupsControllerImpl implements GroupsController {

  @Override
  public ResponseEntity<GroupsResponseDto> getGroups() {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<Void> addGroup(AddGroupDto dto) {
    return ResponseEntity.status(CREATED).build();
  }

  @Override
  public ResponseEntity<Void> deleteGroup(UUID groupId) {
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Void> changeGroup(UUID groupId, Map<String, Object> dto) {
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
