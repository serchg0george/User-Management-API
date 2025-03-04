package com.teamsphere.service;

import com.teamsphere.dto.department.DepartmentDto;
import com.teamsphere.dto.department.DepartmentSearchRequest;
import com.teamsphere.dto.department.DepartmentSearchResponse;
import com.teamsphere.entity.DepartmentEntity;

public interface DepartmentService extends GenericService<DepartmentEntity, DepartmentDto> {

    DepartmentSearchResponse findDepartment(DepartmentSearchRequest request);

}
