package com.api.management.user.service.impl;

import com.api.management.user.dto.position.PositionDto;
import com.api.management.user.entity.PositionEntity;
import com.api.management.user.mapper.PositionMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PositionServiceImpl extends GenericServiceImpl<PositionEntity, PositionDto> {

    private final PositionMapper positionMapper;
    private final PositionRepository positionRepository;

    @Override
    public BaseMapper<PositionEntity, PositionDto> getMapper() {
        return positionMapper;
    }

    @Override
    public JpaRepository<PositionEntity, Long> getRepository() {
        return positionRepository;
    }
}
