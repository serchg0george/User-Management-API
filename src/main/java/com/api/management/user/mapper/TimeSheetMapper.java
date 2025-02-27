package com.api.management.user.mapper;

import com.api.management.user.dto.timesheet.TimeSheetDto;
import com.api.management.user.entity.TimeSheetEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeSheetMapper extends BaseMapper<TimeSheetEntity, TimeSheetDto> {
    @Override
    TimeSheetDto mapEntityToDto(TimeSheetEntity entity);

    @Override
    TimeSheetEntity mapDtoToEntity(TimeSheetDto dto);
}
