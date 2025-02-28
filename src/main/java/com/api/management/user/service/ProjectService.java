package com.api.management.user.service;

import com.api.management.user.dto.project.ProjectDto;
import com.api.management.user.dto.project.ProjectSearchRequest;
import com.api.management.user.dto.project.ProjectSearchResponse;
import com.api.management.user.entity.ProjectEntity;

public interface ProjectService extends GenericService<ProjectEntity, ProjectDto> {

    ProjectSearchResponse findProject(ProjectSearchRequest request);

}
