package com.teamsphere.mapper;

import com.teamsphere.dto.role.RoleDto;
import com.teamsphere.entity.RoleEntity;
import com.teamsphere.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<RoleEntity, RoleDto> {
    @Override
    RoleDto mapEntityToDto(RoleEntity entity);

    @Override
    RoleEntity mapDtoToEntity(RoleDto dto);
}
