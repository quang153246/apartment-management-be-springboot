package com.real_estate.controller;

import com.real_estate.model.dto.request.LoginRequest;
import com.real_estate.model.dto.response.BaseResponseDTO;
import com.real_estate.model.dto.response.LoginResponse;
import com.real_estate.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDTO<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(BaseResponseDTO.<LoginResponse>builder()
                .success(true)
                .message("Login successful")
                .data(authService.login(request))
                .build());
    }

    @PostMapping("/refresh")
    public ResponseEntity<BaseResponseDTO<LoginResponse>> refresh(
            @RequestParam String refreshToken) {
        return ResponseEntity.ok(BaseResponseDTO.<LoginResponse>builder()
                .success(true)
                .message("Token refreshed")
                .data(authService.refreshToken(refreshToken))
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponseDTO<Void>> logout(@RequestParam String username) {
        authService.logout(username);
        return ResponseEntity.ok(BaseResponseDTO.<Void>builder()
                .success(true)
                .message("Logged out successfully")
                .build());
    }

}
