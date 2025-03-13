package com.teamsphere.mapper;

import com.teamsphere.dto.department.DepartmentDto;
import com.teamsphere.entity.DepartmentEntity;
import com.teamsphere.mapper.base.BaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentMapper implements BaseMapper<DepartmentEntity, DepartmentDto> {
    @Override
    public DepartmentDto toDto(DepartmentEntity entity) {
        return DepartmentDto.builder()
                .id(entity.getId())
                .groupName(entity.getGroupName())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public DepartmentEntity toEntity(DepartmentDto dto) {
        return DepartmentEntity.builder()
                .groupName(dto.getGroupName())
                .description(dto.getDescription())
                .build();
    }
}
