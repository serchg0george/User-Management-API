package com.api.management.user.service.impl;

import com.api.management.user.dto.department.DepartmentDto;
import com.api.management.user.entity.DepartmentEntity;
import com.api.management.user.mapper.DepartmentMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl extends GenericServiceImpl<DepartmentEntity, DepartmentDto> {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    @Override
    public BaseMapper<DepartmentEntity, DepartmentDto> getMapper() {
        return departmentMapper;
    }

    @Override
    public JpaRepository<DepartmentEntity, Long> getRepository() {
        return departmentRepository;
    }
}
