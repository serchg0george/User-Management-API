package com.api.management.user.mapper;

import com.api.management.user.dto.PeopleDto;
import com.api.management.user.entity.PeopleEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeopleMapper extends BaseMapper<PeopleEntity, PeopleDto> {

    @Override
    PeopleDto mapEntityToDto(PeopleEntity peopleEntity);

    @Override
    PeopleEntity mapDtoToEntity(PeopleDto peopleDto);

}
