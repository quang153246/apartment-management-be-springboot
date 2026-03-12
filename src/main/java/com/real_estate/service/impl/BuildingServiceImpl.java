package com.real_estate.service.impl;

import com.real_estate.exception.ResourceNotFoundException;
import com.real_estate.model.dto.request.CreateBuildingRequest;
import com.real_estate.model.dto.response.BuildingResponseDTO;
import com.real_estate.model.entity.BuildingEntity;
import com.real_estate.model.entity.ProjectEntity;
import com.real_estate.model.mapper.BuildingMapper;
import com.real_estate.repository.BuildingRepository;
import com.real_estate.repository.ProjectRepository;
import com.real_estate.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final ProjectRepository projectRepository;
    private final BuildingMapper buildingMapper;

    @Override
    public BuildingResponseDTO createBuilding(CreateBuildingRequest request) {
        ProjectEntity project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        BuildingEntity building = BuildingEntity.builder()
                .project(project)
                .name(request.getName())
                .code(request.getCode())
                .floors(request.getFloors())
                .totalApartments(request.getTotalApartments())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();

        BuildingEntity saved = buildingRepository.save(building);

        return buildingMapper.toDTO(saved);
    }

    @Override
    public Page<BuildingResponseDTO> getBuildingsByProjectId(Long projectId, Pageable pageable) {
        Page<BuildingEntity> buildingPage = buildingRepository.findByProjectId(projectId, pageable);

        return buildingPage.map(buildingMapper::toDTO);
    }

    @Override
    public BuildingResponseDTO updateBuilding(Long id, CreateBuildingRequest request) {
        BuildingEntity building = buildingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Building not found"));

        ProjectEntity project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        building.setProject(project);
        building.setName(request.getName());
        building.setCode(request.getCode());
        building.setFloors(request.getFloors());
        building.setTotalApartments(request.getTotalApartments());
        building.setDescription(request.getDescription());
        building.setStatus(request.getStatus());

        BuildingEntity updated = buildingRepository.save(building);

        return buildingMapper.toDTO(updated);
    }

    @Override
    public BuildingResponseDTO getBuildingById(Long id) {
        BuildingEntity building = buildingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Building not found"));

        return buildingMapper.toDTO(building);
    }

    @Override
    public void deleteBuilding(Long id) {
        if (!buildingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Building not found");
        }

        buildingRepository.deleteById(id);
    }
}
