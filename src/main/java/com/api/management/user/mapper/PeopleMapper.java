package com.api.management.user.mapper;

import com.api.management.user.dto.people.PeopleDto;
import com.api.management.user.entity.EmployeeEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeopleMapper extends BaseMapper<EmployeeEntity, PeopleDto> {

    @Override
    PeopleDto mapEntityToDto(EmployeeEntity peopleEntity);

    @Override
    EmployeeEntity mapDtoToEntity(PeopleDto peopleDto);

}
