package com.api.management.user.mapper;

import com.api.management.user.dto.timetracking.TimeTrackingDto;
import com.api.management.user.entity.TimeTrackingEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeTrackingMapper extends BaseMapper<TimeTrackingEntity, TimeTrackingDto> {
    @Override
    TimeTrackingDto mapEntityToDto(TimeTrackingEntity entity);

    @Override
    TimeTrackingEntity mapDtoToEntity(TimeTrackingDto dto);
}
