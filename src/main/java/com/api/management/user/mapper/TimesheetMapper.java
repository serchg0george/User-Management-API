package com.api.management.user.mapper;

import com.api.management.user.dto.timesheet.TimesheetDto;
import com.api.management.user.entity.TimesheetEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimesheetMapper extends BaseMapper<TimesheetEntity, TimesheetDto> {
    @Override
    TimesheetDto mapEntityToDto(TimesheetEntity entity);

    @Override
    TimesheetEntity mapDtoToEntity(TimesheetDto dto);
}
