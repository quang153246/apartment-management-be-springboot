package com.real_estate.model.mapper;

import com.real_estate.model.dto.request.CreateBuildingRequest;
import com.real_estate.model.dto.response.BuildingResponseDTO;
import com.real_estate.model.entity.BuildingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    BuildingEntity toEntity(CreateBuildingRequest request);
    BuildingResponseDTO toDTO(BuildingEntity entity);
}
