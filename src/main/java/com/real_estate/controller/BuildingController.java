package com.real_estate.controller;

import com.real_estate.model.dto.request.CreateBuildingRequest;
import com.real_estate.model.dto.response.BaseResponseDTO;
import com.real_estate.model.dto.response.BuildingResponseDTO;
import com.real_estate.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/buildings")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO<BuildingResponseDTO>> createBuilding(
            @Valid @RequestBody CreateBuildingRequest request) {

        BuildingResponseDTO building = buildingService.createBuilding(request);

        return ResponseEntity.ok(
                BaseResponseDTO.<BuildingResponseDTO>builder()
                        .success(true)
                        .message("Create building successfully")
                        .data(building)
                        .build());
    }

    @GetMapping
    public ResponseEntity<BaseResponseDTO<Page<BuildingResponseDTO>>> getBuildings(
            @RequestParam Long projectId, Pageable pageable) {

        Page<BuildingResponseDTO> buildingPage =
                buildingService.getBuildingsByProjectId(projectId, pageable);

        return ResponseEntity.ok(
                BaseResponseDTO.<Page<BuildingResponseDTO>>builder()
                        .success(true)
                        .message("Get buildings successfully")
                        .data(buildingPage)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<BuildingResponseDTO>> getBuildingById(
            @PathVariable Long id) {

        BuildingResponseDTO building = buildingService.getBuildingById(id);

        return ResponseEntity.ok(
                BaseResponseDTO.<BuildingResponseDTO>builder()
                        .success(true)
                        .message("Get building successfully")
                        .data(building)
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<BuildingResponseDTO>> updateBuilding(
            @PathVariable Long id, @Valid @RequestBody CreateBuildingRequest request) {

        BuildingResponseDTO building = buildingService.updateBuilding(id, request);

        return ResponseEntity.ok(
                BaseResponseDTO.<BuildingResponseDTO>builder()
                        .success(true)
                        .message("Update building successfully")
                        .data(building)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Void>> deleteBuilding(@PathVariable Long id) {

        buildingService.deleteBuilding(id);

        return ResponseEntity.ok(
                BaseResponseDTO.<Void>builder()
                        .success(true)
                        .message("Delete building successfully")
                        .build());
    }
}
