package ru.tecius.presentation.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tecius.api.controller.MenuController;
import ru.tecius.api.dto.menu.permission.request.ChangeMenuItemGroupPermissionsDto;
import ru.tecius.api.dto.menu.permission.request.ChangeMenuItemUserPermissionsDto;
import ru.tecius.api.dto.menu.user.response.MenuItemUsersResponseDto;

@RestController
@RequiredArgsConstructor
public class MenuControllerImpl implements MenuController {

  @Override
  public ResponseEntity<MenuItemUsersResponseDto> getMenuItemUsers(UUID menuItemId) {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<MenuItemUsersResponseDto> getMenuItemGroups(UUID menuItemId) {
    return ResponseEntity.status(OK).build();
  }

  @Override
  public ResponseEntity<Void> changeMenuItemUserPermissions(UUID menuItemId,
      ChangeMenuItemUserPermissionsDto dto) {
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Void> changeMenuItemGroupPermissions(UUID menuItemId,
      ChangeMenuItemGroupPermissionsDto dto) {
    return ResponseEntity.status(NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Void> changeMenuItemPermissions(UUID menuItemId, Map<String, Object> dto) {
    return ResponseEntity.status(NO_CONTENT).build();
  }

}
