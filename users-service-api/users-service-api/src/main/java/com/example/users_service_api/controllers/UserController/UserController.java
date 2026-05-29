package com.example.users_service_api.controllers.UserController;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.dtos.UserDTO;
import com.example.users_service_api.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController implements IUserController{

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserModel> getUser(Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    public ResponseEntity<Void> updateUser(UserDTO userDTO, Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        userService.updateUser(userDTO, userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
