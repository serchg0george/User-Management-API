package com.api.management.user.dto.position;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionSearchResponse {

    private List<PositionDto> positions;

    private Integer positionCount;

}
