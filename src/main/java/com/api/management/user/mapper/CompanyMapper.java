package com.api.management.user.mapper;

import com.api.management.user.dto.company.CompanyDto;
import com.api.management.user.entity.CompanyEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper extends BaseMapper<CompanyEntity, CompanyDto> {
    @Override
    CompanyDto mapEntityToDto(CompanyEntity entity);

    @Override
    CompanyEntity mapDtoToEntity(CompanyDto dto);
}
