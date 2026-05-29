package com.example.users_service_api.services;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.dtos.UserDTO;
import com.example.users_service_api.commons.exception.NotFoundException;
import com.example.users_service_api.repository.IUserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUser(UserDTO userDTO, UUID userId) {
        userRepository.findById(userId)
                .map(existingUser ->
                    updateUserFields(existingUser, userDTO)
                ).map(userRepository::save)
                .orElseThrow(() -> new NotFoundException("User wasn't found for update"));
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new NotFoundException("User wasn't found to delete");
                });
    }

    @Override
    public UserModel getUser(UUID userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("User wasn't found to show"));
    }

    private UserModel updateUserFields(UserModel existingUser, UserDTO userDTO) {
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        return existingUser;
    }
}
