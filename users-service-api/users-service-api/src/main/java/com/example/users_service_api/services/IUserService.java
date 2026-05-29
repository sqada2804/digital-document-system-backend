package com.example.users_service_api.services;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.dtos.UserDTO;

import java.util.UUID;

public interface IUserService {
    void updateUser(UserDTO userDTO, UUID userId);
    void deleteUser(UUID userId);
    UserModel getUser(UUID userId);
}
