package com.teamsphere.service;

import com.teamsphere.dto.role.RoleDto;
import com.teamsphere.dto.role.RoleSearchRequest;
import com.teamsphere.dto.role.RoleSearchResponse;
import com.teamsphere.entity.RoleEntity;

public interface RoleService extends GenericService<RoleEntity, RoleDto> {

    RoleSearchResponse findRole(RoleSearchRequest request);

}
