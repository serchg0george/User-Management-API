package com.api.management.user.mapper;

import com.api.management.user.dto.group.GroupDto;
import com.api.management.user.entity.GroupEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper extends BaseMapper<GroupEntity, GroupDto> {
    @Override
    GroupDto mapEntityToDto(GroupEntity entity);

    @Override
    GroupEntity mapDtoToEntity(GroupDto dto);
}
