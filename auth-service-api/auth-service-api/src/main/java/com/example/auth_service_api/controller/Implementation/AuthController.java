package com.example.auth_service_api.controller.Implementation;

import com.example.auth_service_api.dtos.TokenResponse;
import com.example.auth_service_api.dtos.UserRequest;
import com.example.auth_service_api.controller.IAuthController;
import com.example.auth_service_api.service.Implementation.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }
}
