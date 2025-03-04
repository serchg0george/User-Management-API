package com.teamsphere.service;

import com.teamsphere.dto.position.PositionDto;
import com.teamsphere.dto.position.PositionSearchRequest;
import com.teamsphere.dto.position.PositionSearchResponse;
import com.teamsphere.entity.PositionEntity;

public interface PositionService extends GenericService<PositionEntity, PositionDto> {

    PositionSearchResponse findPosition(PositionSearchRequest searchRequest);

}
