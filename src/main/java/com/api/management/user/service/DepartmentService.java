package com.api.management.user.service;

import com.api.management.user.dto.department.DepartmentDto;
import com.api.management.user.dto.department.DepartmentSearchRequest;
import com.api.management.user.dto.department.DepartmentSearchResponse;
import com.api.management.user.entity.DepartmentEntity;

public interface DepartmentService extends GenericService<DepartmentEntity, DepartmentDto> {

    DepartmentSearchResponse findDepartment(DepartmentSearchRequest request);

}
