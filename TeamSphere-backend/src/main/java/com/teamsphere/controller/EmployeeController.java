package com.teamsphere.controller;

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

import static com.teamsphere.exception.Constants.CREATE_SUCCESS;
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
    public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeDto employee) {
        employeeService.save(employee);
        return ResponseEntity.ok(CREATE_SUCCESS);
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