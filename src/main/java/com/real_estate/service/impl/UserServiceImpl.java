package com.real_estate.service.impl;

import com.real_estate.exception.BadRequestException;
import com.real_estate.exception.ResourceNotFoundException;
import com.real_estate.model.dto.request.ChangePasswordRequest;
import com.real_estate.model.dto.request.RegisterRequest;
import com.real_estate.model.dto.response.UserResponseDTO;
import com.real_estate.model.entity.UserEntity;
import com.real_estate.model.mapper.UserMapper;
import com.real_estate.repository.UserRepositoty;
import com.real_estate.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepositoty userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        UserEntity user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }

//    @Override
//    public UserResponseDTO updateUser(Long id, UpdateUserRequest request) {
//        UserEntity user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        if (!user.getUsername().equals(request.getUsername())
//                && userRepository.existsByUsername(request.getUsername())) {
//            throw new BadRequestException("Username already exists");
//        }
//
//        user.setUsername(request.getUsername());
//        return userMapper.toDTO(userRepository.save(user));
//    }

    @Override
    public void changePassword(Long id, ChangePasswordRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("Current password is incorrect");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("New password and confirm password do not match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void setActiveStatus(Long id, boolean isActive) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setActive(isActive);
        userRepository.save(user);
    }
}