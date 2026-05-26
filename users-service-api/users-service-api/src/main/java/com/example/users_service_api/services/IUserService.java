package com.example.users_service_api.services;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.dtos.UserDTO;

public interface IUserService {
    void updateUser(UserDTO userDTO, Long userId);
    void deleteUser(Long userId);
    UserModel getUser(Long userId);
}
