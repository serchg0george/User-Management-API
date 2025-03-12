package com.teamsphere.controller;

import com.teamsphere.dto.department.DepartmentCreatedResponse;
import com.teamsphere.dto.department.DepartmentDto;
import com.teamsphere.dto.department.DepartmentSearchRequest;
import com.teamsphere.dto.department.DepartmentSearchResponse;
import com.teamsphere.exception.NotFoundException;
import com.teamsphere.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.teamsphere.exception.Constants.UPDATE_SUCCESS;

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
    public ResponseEntity<DepartmentCreatedResponse> createDepartment(@Valid @RequestBody DepartmentDto department) {
        DepartmentDto createdDepartment = departmentService.save(department);
        DepartmentCreatedResponse created = DepartmentCreatedResponse.builder()
                .groupName(createdDepartment.getGroupName())
                .description(createdDepartment.getDescription())
                .build();
        URI location = URI.create("/api/v1/department/%d" + createdDepartment.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId) {
        return ResponseEntity.ok(departmentService.get(departmentId));
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentDto>> getAllDepartments(Pageable pageable) {
        return ResponseEntity.ok(departmentService.getAll(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable("id") Long departmentId,
                                                   @Valid @RequestBody DepartmentDto department) {
        departmentService.update(department, departmentId);
        return ResponseEntity.ok(UPDATE_SUCCESS);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) {
        try {
            departmentService.delete(departmentId);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
