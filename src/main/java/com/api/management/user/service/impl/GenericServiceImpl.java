package com.api.management.user.service.impl;

import com.api.management.user.dto.BaseDto;
import com.api.management.user.entity.BaseEntity;
import com.api.management.user.exception.base.BaseNotFoundException;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.service.GenericService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericServiceImpl<E extends BaseEntity, D extends BaseDto> implements GenericService<E, D> {
    public abstract BaseMapper<E, D> getMapper();

    public abstract JpaRepository<E, Long> getRepository();

    @Override
    public Page<D> getAll(Pageable pageable) {
        return getRepository().findAll(pageable)
                .map(entity -> getMapper().mapEntityToDto(entity));
    }

    @Override
    @Transactional
    public D save(D dto) {
        E entityForSave = getRepository().save(getMapper().mapDtoToEntity(dto));
        return getMapper().mapEntityToDto(entityForSave);
    }

    @Override
    public D get(Long id) {
        E entity = getRepository().findById(id).orElseThrow(() -> new BaseNotFoundException(id));
        return getMapper().mapEntityToDto(entity);
    }

    @Override
    public void delete(Long id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
        } else {
            throw new BaseNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public D update(D dto, Long id) {
        E entityDb = getRepository().findById(id).orElseThrow(() -> new BaseNotFoundException(id));
        E entityForUpdate = getMapper().mapDtoToEntity(dto);
        entityForUpdate.setId(entityDb.getId());
        return getMapper().mapEntityToDto(getRepository().save(entityForUpdate));
    }
}
