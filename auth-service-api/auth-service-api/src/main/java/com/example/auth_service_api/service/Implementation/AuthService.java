package com.example.auth_service_api.service.Implementation;

import com.example.auth_service_api.dtos.LoginRequest;
import com.example.auth_service_api.dtos.TokenResponse;
import com.example.auth_service_api.dtos.UserRequest;
import com.example.auth_service_api.repository.IUserRepository;
import com.example.auth_service_api.service.IAuthService;
import com.example.common_library.entity.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IUserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getUserId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    @Override
    public TokenResponse loginUser(LoginRequest loginRequest) {
        return Optional.of(loginRequest.getEmail())
                .flatMap(userRepository::findByEmail)
                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
                .map(user -> jwtService.generateToken(user.getUserId()))
                .orElseThrow(() -> new RuntimeException("Error trying to login user"));
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .username(userRequest.getUsername())
                .role("USER")
                .build();
    }

}
