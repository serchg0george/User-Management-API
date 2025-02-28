package com.api.management.user.mapper;

import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.entity.EmployeeEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<EmployeeEntity, EmployeeDto> {

    @Override
    EmployeeDto mapEntityToDto(EmployeeEntity peopleEntity);

    @Override
    EmployeeEntity mapDtoToEntity(EmployeeDto peopleDto);

}
