package com.api.management.user.controller;

import com.api.management.user.dto.timesheet.TimesheetDto;
import com.api.management.user.dto.timesheet.TimesheetSearchRequest;
import com.api.management.user.dto.timesheet.TimesheetSearchResponse;
import com.api.management.user.service.TimesheetService;
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
@RequestMapping("api/v1/timesheet")
@Validated
public class TimesheetController {

    private final TimesheetService timesheetService;

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TimesheetSearchResponse> searchTimesheet(@RequestBody TimesheetSearchRequest findTimesheet) {
        return ResponseEntity.ok(timesheetService.findTimesheet(findTimesheet));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createTimesheet(@Valid @RequestBody TimesheetDto timesheet) {
        timesheetService.save(timesheet);
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TimesheetDto> getTimesheetById(@PathVariable("id") Long timesheetId) {
        return new ResponseEntity<>(timesheetService.get(timesheetId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<TimesheetDto>> getAllTimesheets(Pageable pageable) {
        return new ResponseEntity<>(timesheetService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateTimesheet(@PathVariable("id") Long timesheetId,
                                                  @Valid @RequestBody TimesheetDto timesheet) {
        timesheetService.update(timesheet, timesheetId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTimesheet(@PathVariable("id") Long timesheetId) {
        timesheetService.delete(timesheetId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}
