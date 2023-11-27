package com.api.management.user.mapper;

import com.api.management.user.dto.MailDto;
import com.api.management.user.entity.MailEntity;
import com.api.management.user.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MailMapper extends BaseMapper<MailEntity, MailDto> {
    MailDto mapEntityToDto(MailEntity mailEntity);
    MailEntity mapDtoToEntity(MailDto mailDto);
}
