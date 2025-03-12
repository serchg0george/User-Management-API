package com.teamsphere.dto.project;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectCreatedResponse {
    private String name;
    private String description;
    private String startDate;
    private String finishDate;
    private String status;
    private Long companyId;
}
