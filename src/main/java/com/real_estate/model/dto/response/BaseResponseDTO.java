package com.real_estate.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDTO<T> {
    private T data;
    private Boolean success;
    private String message;
}
