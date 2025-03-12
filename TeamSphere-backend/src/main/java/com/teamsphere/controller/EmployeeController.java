package com.teamsphere.controller;

import com.teamsphere.dto.employee.EmployeeCreatedResponse;
import com.teamsphere.dto.employee.EmployeeDto;
import com.teamsphere.dto.employee.EmployeeSearchRequest;
import com.teamsphere.dto.employee.EmployeeSearchResponse;
import com.teamsphere.exception.NotFoundException;
import com.teamsphere.service.EmployeeService;
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
@RequestMapping("api/v1/employee")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/search")
    public ResponseEntity<EmployeeSearchResponse> searchEmployeeByCriteria(@RequestBody EmployeeSearchRequest findEmployee) {
        return ResponseEntity.ok(employeeService.findEmployee(findEmployee));
    }

    @PostMapping
    public ResponseEntity<EmployeeCreatedResponse> createEmployee(@Valid @RequestBody EmployeeDto employee) {
        EmployeeDto createdEmployee = employeeService.save(employee);
        EmployeeCreatedResponse created = EmployeeCreatedResponse.builder()
                .firstName(createdEmployee.getFirstName())
                .lastName(createdEmployee.getLastName())
                .pin(createdEmployee.getPin())
                .address(createdEmployee.getAddress())
                .email(createdEmployee.getEmail())
                .departmentId(createdEmployee.getDepartmentId())
                .positionId(createdEmployee.getPositionId())
                .taskIds(createdEmployee.getTaskIds())
                .projectIds(createdEmployee.getProjectIds())
                .build();
        URI location = URI.create("/api/v1/employee/%d" + createdEmployee.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        return ResponseEntity.ok(employeeService.get(employeeId));
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAllEmployees(Pageable pageable) {
        return ResponseEntity.ok(employeeService.getAll(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long employeeId,
                                                 @Valid @RequestBody EmployeeDto employee) {
        employeeService.update(employee, employeeId);
        return ResponseEntity.ok(UPDATE_SUCCESS);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        try {
            employeeService.delete(employeeId);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}