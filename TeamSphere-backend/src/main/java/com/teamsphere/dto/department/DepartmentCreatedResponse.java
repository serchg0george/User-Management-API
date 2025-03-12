package com.teamsphere.dto.department;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentCreatedResponse {
    private String groupName;
    private String description;
}
