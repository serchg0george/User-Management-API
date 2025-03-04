package com.teamsphere.dto.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentSearchResponse {

    private List<DepartmentDto> departments;

    private Integer departmentCount;

}
