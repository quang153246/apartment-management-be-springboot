package com.real_estate.model.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseDTO<T> {
    private List<T> content;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean last;
}
