package com.api.management.user.mapper.base;

public interface BaseMapper<E, D> {
    D mapEntityToDto(E entity);
    E mapDtoToEntity(D dto);
}
