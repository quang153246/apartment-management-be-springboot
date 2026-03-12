package com.real_estate.controller;

import com.real_estate.model.dto.request.CreateProjectRequest;

import com.real_estate.model.dto.response.BaseResponseDTO;
import com.real_estate.model.dto.response.ProjectResponse;
import com.real_estate.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/projects")
@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("")
    ResponseEntity<BaseResponseDTO<ProjectResponse>> createProject(@RequestBody CreateProjectRequest request) {
        ProjectResponse project = projectService.createProject(request);

        return ResponseEntity.ok()
                .body( BaseResponseDTO.<ProjectResponse>builder()
                .success(true)
                .message("Create project successfully")
                .data(project)
                .build());
    }

    @GetMapping("")
    ResponseEntity<BaseResponseDTO<Page<ProjectResponse>>> getList(Pageable pageable) {

        Page<ProjectResponse> projectPage = projectService.getProjects(pageable);

        return ResponseEntity.ok(
                BaseResponseDTO.<Page<ProjectResponse>>builder()
                        .success(true)
                        .message("Get projects successfully")
                        .data(projectPage)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<ProjectResponse>> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectService.getProjectById(id);

        return ResponseEntity.ok(
                BaseResponseDTO.<ProjectResponse>builder()
                        .success(true)
                        .message("Get project details successfully")
                        .data(project)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Void>> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);

        return ResponseEntity.ok(
                BaseResponseDTO.<Void>builder()
                        .success(true)
                        .message("Delete project successfully")
                        .data(null)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<ProjectResponse>> updateProject(
            @PathVariable Long id,
            @RequestBody CreateProjectRequest request) {

        ProjectResponse response = projectService.updateProject(id, request);

        return ResponseEntity.ok(
                BaseResponseDTO.<ProjectResponse>builder()
                        .success(true)
                        .message("Update project successfully")
                        .data(response)
                        .build()
        );
    }
}
