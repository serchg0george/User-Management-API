package com.teamsphere.mapper.base;

public interface BaseMapper<E, D> {
    D mapEntityToDto(E entity);
    E mapDtoToEntity(D dto);
}
