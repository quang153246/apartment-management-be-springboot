package com.real_estate.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuildingResponseDTO {
    private Long id;

    private Long projectId;

    private String projectName;

    private String name;

    private String code;

    private Integer floors;

    private Integer totalApartments;

    private String description;

    private String status;
}
