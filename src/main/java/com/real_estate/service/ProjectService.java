package com.real_estate.service;

import com.real_estate.model.dto.request.CreateProjectRequest;
import com.real_estate.model.dto.response.PageResponseDTO;
import com.real_estate.model.dto.response.ProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    ProjectResponse createProject(CreateProjectRequest project);
    Page<ProjectResponse> getProjects(Pageable pageable);
    ProjectResponse getProjectById(Long id);
    ProjectResponse updateProject(Long id, CreateProjectRequest project);
    void deleteProject(Long id);
}
