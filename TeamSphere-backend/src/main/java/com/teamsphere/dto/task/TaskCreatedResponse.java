package com.teamsphere.dto.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskCreatedResponse {
    private Integer timeSpentMinutes;
    private String taskDescription;
    private Long roleId;
}
