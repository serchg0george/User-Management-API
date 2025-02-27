package com.api.management.user.mapper;

import com.api.management.user.dto.project.ProjectDto;
import com.api.management.user.entity.ProjectEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<ProjectEntity, ProjectDto> {
    @Override
    ProjectDto mapEntityToDto(ProjectEntity entity);

    @Override
    ProjectEntity mapDtoToEntity(ProjectDto dto);
}
