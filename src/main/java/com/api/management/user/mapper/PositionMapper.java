package com.api.management.user.mapper;

import com.api.management.user.dto.position.PositionDto;
import com.api.management.user.entity.PositionEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper extends BaseMapper<PositionEntity, PositionDto> {
    @Override
    PositionDto mapEntityToDto(PositionEntity entity);

    @Override
    PositionEntity mapDtoToEntity(PositionDto dto);
}
