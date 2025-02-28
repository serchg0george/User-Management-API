package com.api.management.user.controller;

import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.dto.employee.SearchEmployeeResponse;
import com.api.management.user.dto.search.PeopleSearchRequest;
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
@RequestMapping("api/v1/people")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SearchEmployeeResponse> searchEmployeeByCriteria(@RequestBody PeopleSearchRequest findPerson) {
        return ResponseEntity.ok(employeeService.findEmployee(findPerson));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createPeople(@Valid @RequestBody EmployeeDto people) {
        employeeService.save(people);
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<EmployeeDto> getPeopleById(@PathVariable("id") Long peopleId) {
        return new ResponseEntity<>(employeeService.get(peopleId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<EmployeeDto>> getAllPeople(Pageable pageable) {
        return new ResponseEntity<>(employeeService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updatePeople(@PathVariable("id") Long peopleId,
                                               @Valid @RequestBody EmployeeDto people) {
        employeeService.update(people, peopleId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePeople(@PathVariable("id") Long peopleId) {
        employeeService.delete(peopleId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}