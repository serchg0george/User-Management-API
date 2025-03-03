package com.api.management.user.controller;

import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.dto.employee.EmployeeSearchResponse;
import com.api.management.user.dto.employee.EmployeeSearchRequest;
import com.api.management.user.service.EmployeeService;
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
@RequestMapping("api/v1/employee")
@Validated
@CrossOrigin(origins = "http://localhost:3000/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<EmployeeSearchResponse> searchEmployeeByCriteria(@RequestBody EmployeeSearchRequest findEmployee) {
        return ResponseEntity.ok(employeeService.findEmployee(findEmployee));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeDto employee) {
        employeeService.save(employee);
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        return new ResponseEntity<>(employeeService.get(employeeId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<EmployeeDto>> getAllEmployees(Pageable pageable) {
        return new ResponseEntity<>(employeeService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long employeeId,
                                               @Valid @RequestBody EmployeeDto employee) {
        employeeService.update(employee, employeeId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.delete(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}