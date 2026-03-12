package com.real_estate.model.dto.response;

import java.time.LocalDate;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private Long id;

    private String name;

    private String address;

    private String developer;

    private String description;

    private String status;

    private LocalDate startDate;

    private String investor;

    private String thumbnail;
}
