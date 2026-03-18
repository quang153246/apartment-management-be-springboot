package com.real_estate.service;

import com.real_estate.model.dto.request.LoginRequest;
import com.real_estate.model.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse refreshToken(String refreshToken);
    void logout(String username);
}
