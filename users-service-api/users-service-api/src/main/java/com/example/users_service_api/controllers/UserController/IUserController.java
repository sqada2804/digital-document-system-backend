package com.example.users_service_api.controllers.UserController;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.constants.ApiPathConstants;
import com.example.users_service_api.commons.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.USER_ROUTE)
public interface IUserController {
    @GetMapping("/get")
    ResponseEntity<UserModel> getUser(@AuthenticationPrincipal Jwt jwt);
    @PutMapping("/update")
    ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO, @AuthenticationPrincipal Jwt jwt);
    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteUser(@AuthenticationPrincipal Jwt jwt);
}
