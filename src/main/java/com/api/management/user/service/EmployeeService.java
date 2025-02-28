package com.api.management.user.service;

import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.dto.employee.EmployeeSearchResponse;
import com.api.management.user.dto.employee.EmployeeSearchRequest;
import com.api.management.user.entity.EmployeeEntity;

public interface EmployeeService extends GenericService<EmployeeEntity, EmployeeDto> {

    EmployeeSearchResponse findEmployee(EmployeeSearchRequest request);

}
