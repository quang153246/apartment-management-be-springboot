package com.real_estate.service;

import com.real_estate.exception.BadRequestException;
import com.real_estate.exception.ResourceNotFoundException;
import com.real_estate.model.entity.RefreshTokenEntity;
import com.real_estate.model.entity.UserEntity;
import com.real_estate.repository.RefreshTokenRepository;
import com.real_estate.repository.UserRepositoty;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {
    @Value("${app.refresh-token.expiration-days}")
    private long expirationDays;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepositoty userRepository;

    public RefreshTokenEntity createRefreshToken(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        refreshTokenRepository.deleteByUser(user);

        RefreshTokenEntity token = RefreshTokenEntity.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plus(expirationDays, ChronoUnit.DAYS))
                .build();

        return refreshTokenRepository.save(token);
    }

    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new BadRequestException("Refresh token expired. Please login again.");
        }
        return token;
    }

    public void deleteByUsername(String username) {
        userRepository.findByUsername(username)
                .ifPresent(refreshTokenRepository::deleteByUser);
    }
}
