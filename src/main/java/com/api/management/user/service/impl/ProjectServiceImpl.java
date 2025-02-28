package com.api.management.user.service.impl;

import com.api.management.user.dto.project.ProjectDto;
import com.api.management.user.entity.ProjectEntity;
import com.api.management.user.mapper.ProjectMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectServiceImpl extends GenericServiceImpl<ProjectEntity, ProjectDto> {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    @Override
    public BaseMapper<ProjectEntity, ProjectDto> getMapper() {
        return projectMapper;
    }

    @Override
    public JpaRepository<ProjectEntity, Long> getRepository() {
        return projectRepository;
    }
}
