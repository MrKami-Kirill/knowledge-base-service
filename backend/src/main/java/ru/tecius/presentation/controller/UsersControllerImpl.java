package ru.tecius.presentation.controller;

import static org.springframework.http.HttpStatus.OK;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tecius.api.controller.UsersController;
import ru.tecius.api.dto.user.response.UsersResponseDto;

@RestController
@RequiredArgsConstructor
public class UsersControllerImpl implements UsersController {

  @Override
  public ResponseEntity<UsersResponseDto> getUsers() {
    return ResponseEntity.status(OK).build();
  }


}
