package com.real_estate.service.impl;

import com.real_estate.exception.UnauthorizedException;
import com.real_estate.model.dto.request.LoginRequest;
import com.real_estate.model.dto.response.LoginResponse;
import com.real_estate.model.entity.RefreshTokenEntity;
import com.real_estate.model.entity.UserEntity;
import com.real_estate.repository.RefreshTokenRepository;
import com.real_estate.repository.UserRepositoty;
import com.real_estate.service.AuthService;
import com.real_estate.service.JwtService;
import com.real_estate.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepositoty userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid username or password");
        }

        if (!user.isActive()) {
            throw new UnauthorizedException("Account is disabled");
        }

        String accessToken = jwtService.generateToken(user.getUsername());
        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .username(user.getUsername())
                .build();
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        RefreshTokenEntity token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new UnauthorizedException("Invalid refresh token"));

        refreshTokenService.verifyExpiration(token);

        String newAccessToken = jwtService.generateToken(token.getUser().getUsername());

        return LoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(token.getToken())
                .username(token.getUser().getUsername())
                .build();
    }

    @Override
    public void logout(String username) {
        refreshTokenService.deleteByUsername(username);
    }
}