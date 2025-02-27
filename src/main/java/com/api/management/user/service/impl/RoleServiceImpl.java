package com.api.management.user.service.impl;

import com.api.management.user.dto.role.RoleDto;
import com.api.management.user.entity.RoleEntity;
import com.api.management.user.mapper.RoleMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl extends GenericServiceImpl<RoleEntity, RoleDto> {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Override
    public BaseMapper<RoleEntity, RoleDto> getMapper() {
        return roleMapper;
    }

    @Override
    public JpaRepository<RoleEntity, Long> getRepository() {
        return roleRepository;
    }
}
