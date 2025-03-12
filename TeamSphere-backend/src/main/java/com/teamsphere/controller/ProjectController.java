package com.teamsphere.controller;

import com.teamsphere.dto.project.ProjectDto;
import com.teamsphere.dto.project.ProjectSearchRequest;
import com.teamsphere.dto.project.ProjectSearchResponse;
import com.teamsphere.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.teamsphere.exception.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

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
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Long projectId) {
        return new ResponseEntity<>(projectService.get(projectId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectDto>> getAllProjects(Pageable pageable) {
        return new ResponseEntity<>(projectService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProject(@PathVariable("id") Long projectId,
                                                @Valid @RequestBody ProjectDto project) {
        projectService.update(project, projectId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Long projectId) {
        projectService.delete(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}
