package com.teamsphere.dto.position;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PositionCreatedResponse {
    private String positionName;
    private Integer yearsOfExperience;
}
