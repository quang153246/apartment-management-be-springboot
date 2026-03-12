package com.real_estate.service.impl;

import com.real_estate.exception.ResourceNotFoundException;
import com.real_estate.model.dto.response.PageResponseDTO;
import com.real_estate.model.entity.ProjectEntity;
import com.real_estate.model.mapper.ProjectMapper;
import com.real_estate.model.dto.request.CreateProjectRequest;
import com.real_estate.model.dto.response.ProjectResponse;
import com.real_estate.repository.ProjectRepository;
import com.real_estate.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(CreateProjectRequest request) {
        ProjectEntity saved = projectRepository.save(projectMapper.toEntity((request)));
        return projectMapper.toDTO(saved);
    }

    @Override
    public Page<ProjectResponse> getProjects(Pageable pageable) {
        Page<ProjectEntity> projectPage =  projectRepository.findAll(pageable);

        return projectPage. map(projectMapper::toDTO);
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        ProjectEntity project = projectRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Project not found"));
        return projectMapper.toDTO(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, CreateProjectRequest project) {
        ProjectEntity updateProject = projectRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Project not found"));

        updateProject.setName(project.getName());
        updateProject.setAddress(project.getAddress());
        updateProject.setDeveloper(project.getDeveloper());
        updateProject.setDescription(project.getDescription());
        updateProject.setStatus(project.getStatus());
        updateProject.setStartDate(project.getStartDate());
        updateProject.setInvestor(project.getInvestor());
        updateProject.setThumbnail(project.getThumbnail());

        projectRepository.save(updateProject);

        return projectMapper.toDTO(updateProject);
    }

    @Override
    public void deleteProject(Long id) {
        ProjectEntity updateProject = projectRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Project not found"));

        projectRepository.deleteById(id);
    }

}
