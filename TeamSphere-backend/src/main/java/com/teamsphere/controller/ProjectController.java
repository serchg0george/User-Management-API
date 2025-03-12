package com.teamsphere.controller;

import com.teamsphere.dto.project.ProjectDto;
import com.teamsphere.dto.project.ProjectSearchRequest;
import com.teamsphere.dto.project.ProjectSearchResponse;
import com.teamsphere.exception.NotFoundException;
import com.teamsphere.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.teamsphere.exception.Constants.CREATE_SUCCESS;
import static com.teamsphere.exception.Constants.UPDATE_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/project")
@Validated
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/search")
    public ResponseEntity<ProjectSearchResponse> searchProject(@RequestBody ProjectSearchRequest findProject) {
        return ResponseEntity.ok(projectService.findProject(findProject));
    }

    @PostMapping
    public ResponseEntity<String> createProject(@Valid @RequestBody ProjectDto project) {
        projectService.save(project);
        return ResponseEntity.ok(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Long projectId) {
        return ResponseEntity.ok(projectService.get(projectId));
    }

    @GetMapping
    public ResponseEntity<Page<ProjectDto>> getAllProjects(Pageable pageable) {
        return ResponseEntity.ok(projectService.getAll(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProject(@PathVariable("id") Long projectId,
                                                @Valid @RequestBody ProjectDto project) {
        projectService.update(project, projectId);
        return ResponseEntity.ok(UPDATE_SUCCESS);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Long projectId) {
        try {
            projectService.delete(projectId);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
