package com.api.management.user.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchEmployeeResponse {

    private List<EmployeeDto> people;

    private Integer employeeCount;
}
