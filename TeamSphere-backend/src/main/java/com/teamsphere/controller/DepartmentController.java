package com.teamsphere.controller;

import com.teamsphere.dto.department.DepartmentDto;
import com.teamsphere.dto.department.DepartmentSearchRequest;
import com.teamsphere.dto.department.DepartmentSearchResponse;
import com.teamsphere.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.teamsphere.exception.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/department")
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/search")
    public ResponseEntity<DepartmentSearchResponse> searchDepartment(@RequestBody DepartmentSearchRequest findDepartment) {
        return ResponseEntity.ok(departmentService.findDepartment(findDepartment));
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@Valid @RequestBody DepartmentDto department) {
        departmentService.save(department);
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId) {
        return new ResponseEntity<>(departmentService.get(departmentId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentDto>> getAllDepartments(Pageable pageable) {
        return new ResponseEntity<>(departmentService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable("id") Long departmentId,
                                                   @Valid @RequestBody DepartmentDto department) {
        departmentService.update(department, departmentId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.delete(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}
