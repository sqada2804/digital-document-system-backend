package com.example.users_service_api.services;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.dtos.UserDTO;
import com.example.users_service_api.commons.exception.NotFoundException;
import com.example.users_service_api.repository.IUserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUser(UserDTO userDTO, String userId) {
        userRepository.findById(Long.valueOf(userId))
                .map(existingUser -> {
                    return updateUserFields(existingUser, userDTO);
                }).map(userRepository::save)
                .orElseThrow(() -> new NotFoundException("User wasn't found for update"));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.findById(Long.valueOf(userId))
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new NotFoundException("User wasn't found to delete");
                });
    }

    @Override
    public UserModel getUser(String userId) {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if(authenticationToken == null){
            throw new RuntimeException("Auth is null");
        }
        return Optional.of(Long.valueOf(userId))
                .flatMap(userRepository::findByUserId).orElseThrow(() -> new NotFoundException("User wasn't found to show"));
    }

    private UserModel updateUserFields(UserModel existingUser, UserDTO userDTO) {
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        return existingUser;
    }
}
