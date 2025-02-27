package com.api.management.user.mapper;

import com.api.management.user.dto.role.RoleDto;
import com.api.management.user.entity.RoleEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<RoleEntity, RoleDto> {
    @Override
    RoleDto mapEntityToDto(RoleEntity entity);

    @Override
    RoleEntity mapDtoToEntity(RoleDto dto);
}
