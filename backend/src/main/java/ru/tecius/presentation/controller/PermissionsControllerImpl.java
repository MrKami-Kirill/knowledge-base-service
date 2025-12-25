package ru.tecius.presentation.controller;

import static org.springframework.http.HttpStatus.OK;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tecius.api.controller.PermissionsController;
import ru.tecius.api.dto.permission.response.PermissionsResponseDto;

@RestController
@RequiredArgsConstructor
public class PermissionsControllerImpl implements PermissionsController {

  @Override
  public ResponseEntity<PermissionsResponseDto> getPermissions() {
    return ResponseEntity.status(OK).build();
  }
}
