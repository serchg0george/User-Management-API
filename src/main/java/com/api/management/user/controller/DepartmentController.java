package com.api.management.user.controller;

import com.api.management.user.dto.department.DepartmentDto;
import com.api.management.user.dto.department.DepartmentSearchRequest;
import com.api.management.user.dto.department.DepartmentSearchResponse;
import com.api.management.user.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.api.management.user.exception.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/department")
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<DepartmentSearchResponse> searchDepartment(@RequestBody DepartmentSearchRequest findDepartment) {
        return ResponseEntity.ok(departmentService.findDepartment(findDepartment));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createDepartment(@Valid @RequestBody DepartmentDto department) {
        departmentService.save(department);
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId) {
        return new ResponseEntity<>(departmentService.get(departmentId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<DepartmentDto>> getAllDepartments(Pageable pageable) {
        return new ResponseEntity<>(departmentService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateDepartment(@PathVariable("id") Long departmentId,
                                                   @Valid @RequestBody DepartmentDto department) {
        departmentService.update(department, departmentId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.delete(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}
