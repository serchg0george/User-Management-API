package com.api.management.user.mapper;

import com.api.management.user.dto.department.DepartmentDto;
import com.api.management.user.entity.DepartmentEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends BaseMapper<DepartmentEntity, DepartmentDto> {
    @Override
    DepartmentDto mapEntityToDto(DepartmentEntity entity);

    @Override
    DepartmentEntity mapDtoToEntity(DepartmentDto dto);
}
