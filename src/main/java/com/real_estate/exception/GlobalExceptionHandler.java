package com.real_estate.exception;

import com.real_estate.model.dto.response.BaseResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponseDTO<Object>> handleNotFound(ResourceNotFoundException ex) {

        BaseResponseDTO<Object> response =
                BaseResponseDTO.builder().success(false).message(ex.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponseDTO<Object>> handleBadRequest(BadRequestException ex) {

        BaseResponseDTO<Object> response =
                BaseResponseDTO.builder().success(false).message(ex.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponseDTO<Object>> handleValidationException(
            MethodArgumentNotValidException ex) {

        String message =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(e -> e.getField() + ": " + e.getDefaultMessage())
                        .collect(Collectors.joining(", "));

        BaseResponseDTO<Object> response =
                BaseResponseDTO.builder().success(false).message(message).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseDTO<Object>> handleException(Exception ex) {

        BaseResponseDTO<Object> response =
                BaseResponseDTO.builder().success(false).message("Internal server error").build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<BaseResponseDTO<Object>> handleUnauthorized(UnauthorizedException ex) {
        BaseResponseDTO<Object> response = BaseResponseDTO.builder()
                .success(false)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
