package com.real_estate.model.mapper;

import com.real_estate.model.dto.request.CreateProjectRequest;
import com.real_estate.model.dto.response.ProjectResponse;
import com.real_estate.model.entity.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectEntity toEntity(CreateProjectRequest request);

    ProjectResponse toDTO(ProjectEntity project);
}
