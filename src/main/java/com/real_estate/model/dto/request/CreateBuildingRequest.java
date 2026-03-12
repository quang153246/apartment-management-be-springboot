package com.real_estate.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBuildingRequest {
    @NotNull(message = "Project id is required")
    private Long projectId;

    @NotBlank(message = "Building name is required")
    private String name;

    @NotBlank(message = "Building code is required")
    private String code;

    @NotNull(message = "Floors is required")
    private Integer floors;

    private Integer totalApartments;

    private String description;

    private String status;
}
