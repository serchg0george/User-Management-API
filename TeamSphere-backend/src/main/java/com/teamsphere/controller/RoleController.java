package com.teamsphere.controller;

import com.teamsphere.dto.role.RoleDto;
import com.teamsphere.dto.role.RoleSearchRequest;
import com.teamsphere.dto.role.RoleSearchResponse;
import com.teamsphere.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.teamsphere.exception.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/role")
@Validated
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RoleSearchResponse> searchRole(@RequestBody RoleSearchRequest findRole) {
        return ResponseEntity.ok(roleService.findRole(findRole));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createRole(@Valid @RequestBody RoleDto role) {
        roleService.save(role);
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") Long roleId) {
        return new ResponseEntity<>(roleService.get(roleId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<RoleDto>> getAllRoles(Pageable pageable) {
        return new ResponseEntity<>(roleService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateRole(@PathVariable("id") Long roleId,
                                             @Valid @RequestBody RoleDto role) {
        roleService.update(role, roleId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long roleId) {
        roleService.delete(roleId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}
