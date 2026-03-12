package com.real_estate.service;

import com.real_estate.model.dto.request.CreateBuildingRequest;
import com.real_estate.model.dto.response.BuildingResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuildingService {
    BuildingResponseDTO createBuilding(CreateBuildingRequest request);

    Page<BuildingResponseDTO> getBuildingsByProjectId(Long projectId, Pageable pageable);

    BuildingResponseDTO updateBuilding(Long id, CreateBuildingRequest request);

    BuildingResponseDTO getBuildingById(Long id);

    void deleteBuilding(Long id);
}
