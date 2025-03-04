package com.teamsphere.service;

import com.teamsphere.dto.employee.EmployeeDto;
import com.teamsphere.dto.employee.EmployeeSearchResponse;
import com.teamsphere.dto.employee.EmployeeSearchRequest;
import com.teamsphere.entity.EmployeeEntity;

public interface EmployeeService extends GenericService<EmployeeEntity, EmployeeDto> {

    EmployeeSearchResponse findEmployee(EmployeeSearchRequest request);

}
