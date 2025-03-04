package com.teamsphere.mapper;

import com.teamsphere.dto.company.CompanyDto;
import com.teamsphere.entity.CompanyEntity;
import com.teamsphere.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper extends BaseMapper<CompanyEntity, CompanyDto> {
    @Override
    CompanyDto mapEntityToDto(CompanyEntity entity);

    @Override
    CompanyEntity mapDtoToEntity(CompanyDto dto);
}
