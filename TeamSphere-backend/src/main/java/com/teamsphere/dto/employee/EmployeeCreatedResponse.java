package com.teamsphere.dto.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EmployeeCreatedResponse {
    private String firstName;
    private String lastName;
    private String pin;
    private String address;
    private String email;
    private Long departmentId;
    private Long positionId;
    private List<Long> taskIds;
    private List<Long> projectIds;
}
