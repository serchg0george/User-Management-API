package com.teamsphere.controller;

import com.teamsphere.dto.employee.EmployeeDto;
import com.teamsphere.dto.employee.EmployeeSearchRequest;
import com.teamsphere.dto.employee.EmployeeSearchResponse;
import com.teamsphere.service.EmployeeService;
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
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        return new ResponseEntity<>(employeeService.get(employeeId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAllEmployees(Pageable pageable) {
        return new ResponseEntity<>(employeeService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long employeeId,
                                                 @Valid @RequestBody EmployeeDto employee) {
        employeeService.update(employee, employeeId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.delete(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}