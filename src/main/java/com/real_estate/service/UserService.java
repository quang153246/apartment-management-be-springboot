package com.real_estate.service;

import com.real_estate.model.dto.request.ChangePasswordRequest;
import com.real_estate.model.dto.request.RegisterRequest;
import com.real_estate.model.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO register(RegisterRequest request);

    UserResponseDTO getUserById(Long id);

//    UserResponseDTO updateUser(Long id, UpdateUserRequest request);

    void changePassword(Long id, ChangePasswordRequest request);

    void setActiveStatus(Long id, boolean isActive);
}