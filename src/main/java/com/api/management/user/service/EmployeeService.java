package com.api.management.user.service;

import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.dto.employee.SearchEmployeeResponse;
import com.api.management.user.dto.search.PeopleSearchRequest;
import com.api.management.user.entity.EmployeeEntity;

public interface EmployeeService extends GenericService<EmployeeEntity, EmployeeDto> {

    SearchEmployeeResponse findEmployee(PeopleSearchRequest request);

}
