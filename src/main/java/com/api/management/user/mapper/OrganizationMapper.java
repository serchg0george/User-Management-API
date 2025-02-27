package com.api.management.user.mapper;

import com.api.management.user.dto.organization.OrganizationDto;
import com.api.management.user.entity.OrganizationEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper<OrganizationEntity, OrganizationDto> {
    @Override
    OrganizationDto mapEntityToDto(OrganizationEntity entity);

    @Override
    OrganizationEntity mapDtoToEntity(OrganizationDto dto);
}
