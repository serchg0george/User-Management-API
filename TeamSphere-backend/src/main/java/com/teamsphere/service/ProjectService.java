package com.teamsphere.service;

import com.teamsphere.dto.project.ProjectDto;
import com.teamsphere.dto.project.ProjectSearchRequest;
import com.teamsphere.dto.project.ProjectSearchResponse;
import com.teamsphere.entity.ProjectEntity;

public interface ProjectService extends GenericService<ProjectEntity, ProjectDto> {

    ProjectSearchResponse findProject(ProjectSearchRequest request);

}
