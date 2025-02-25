package com.api.management.user.controller;

import com.api.management.user.dto.MailToPeopleDto;
import com.api.management.user.dto.PeopleDto;
import com.api.management.user.service.PeopleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.api.management.user.controller.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/people")
@Validated
public class PeopleController {

    private final PeopleService peopleService;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PeopleDto> setMailToPeople(@RequestBody MailToPeopleDto mailToPeopleDto) {
        PeopleDto people = peopleService.setMailToPeople(mailToPeopleDto.mailId(), mailToPeopleDto.peopleId());
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<PeopleDto>> searchPeopleByName(@RequestParam("query") String findPerson) {
        return ResponseEntity.ok(peopleService.searchPeopleByName(findPerson));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createPeople(@Valid @RequestBody PeopleDto people) {
        peopleService.save(people);
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PeopleDto> getPeopleById(@PathVariable("id") Long peopleId) {
        return new ResponseEntity<>(peopleService.get(peopleId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<PeopleDto>> getAllPeople(Pageable pageable) {
        return new ResponseEntity<>(peopleService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updatePeople(@PathVariable("id") Long peopleId,
                                               @Valid @RequestBody PeopleDto people) {
        peopleService.update(people, peopleId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePeople(@PathVariable("id") Long peopleId) {
        peopleService.delete(peopleId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}