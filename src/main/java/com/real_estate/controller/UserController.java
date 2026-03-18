package com.real_estate.controller;

import com.real_estate.model.dto.request.ChangePasswordRequest;
import com.real_estate.model.dto.request.RegisterRequest;
import com.real_estate.model.dto.response.BaseResponseDTO;
import com.real_estate.model.dto.response.UserResponseDTO;
import com.real_estate.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDTO<UserResponseDTO>> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponseDTO.<UserResponseDTO>builder()
                        .success(true)
                        .message("User registered successfully")
                        .data(userService.register(request))
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<UserResponseDTO>> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(BaseResponseDTO.<UserResponseDTO>builder()
                .success(true)
                .message("Get user successfully")
                .data(userService.getUserById(id))
                .build());
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<BaseResponseDTO<UserResponseDTO>> updateUser(
//            @PathVariable Long id,
//            @Valid @RequestBody UpdateUserRequest request) {
//
//        return ResponseEntity.ok(BaseResponseDTO.<UserResponseDTO>builder()
//                .success(true)
//                .message("Update user successfully")
//                .data(userService.updateUser(id, request))
//                .build());
//    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<BaseResponseDTO<Void>> changePassword(
            @PathVariable Long id,
            @Valid @RequestBody ChangePasswordRequest request) {

        userService.changePassword(id, request);
        return ResponseEntity.ok(BaseResponseDTO.<Void>builder()
                .success(true)
                .message("Password changed successfully")
                .build());
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<BaseResponseDTO<Void>> setActiveStatus(
            @PathVariable Long id,
            @RequestParam boolean isActive) {

        userService.setActiveStatus(id, isActive);
        return ResponseEntity.ok(BaseResponseDTO.<Void>builder()
                .success(true)
                .message("User status updated successfully")
                .build());
    }
}