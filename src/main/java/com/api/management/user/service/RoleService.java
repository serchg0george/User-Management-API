package com.api.management.user.service;

import com.api.management.user.dto.role.RoleDto;
import com.api.management.user.dto.role.RoleSearchRequest;
import com.api.management.user.dto.role.RoleSearchResponse;
import com.api.management.user.entity.RoleEntity;

public interface RoleService extends GenericService<RoleEntity, RoleDto> {

    RoleSearchResponse findRole(RoleSearchRequest request);

}
