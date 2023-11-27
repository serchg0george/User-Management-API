package com.api.management.user.mapper;

import com.api.management.user.dto.AddressDto;
import com.api.management.user.entity.AddressEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<AddressEntity, AddressDto> {
    @Override
    AddressDto mapEntityToDto(AddressEntity entity);

    @Override
    AddressEntity mapDtoToEntity(AddressDto dto);

}
