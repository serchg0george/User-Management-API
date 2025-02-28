package com.api.management.user.service;

import com.api.management.user.dto.position.PositionDto;
import com.api.management.user.dto.position.PositionSearchRequest;
import com.api.management.user.dto.position.PositionSearchResponse;
import com.api.management.user.entity.PositionEntity;

public interface PositionService extends GenericService<PositionEntity, PositionDto> {

    PositionSearchResponse findPosition(PositionSearchRequest searchRequest);

}
