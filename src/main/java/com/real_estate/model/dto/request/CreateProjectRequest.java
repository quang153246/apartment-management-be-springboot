package com.real_estate.model.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProjectRequest {
    private String name;

    private String address;

    private String developer;

    private String description;

    private String status;

    private LocalDate startDate;

    private String investor;

    private String thumbnail;
}
