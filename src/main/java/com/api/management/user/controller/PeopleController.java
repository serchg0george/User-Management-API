package com.api.management.user.controller;

import com.api.management.user.dto.people.MailToPeopleDto;
import com.api.management.user.dto.people.PeopleDto;
import com.api.management.user.dto.people.SearchPeopleResponse;
import com.api.management.user.dto.search.PeopleSearchRequest;
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

import static com.api.management.user.exception.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/people")
@Validated
public class PeopleController {

    private final PeopleService peopleService;

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SearchPeopleResponse> searchPeopleByCriteria(@RequestBody PeopleSearchRequest findPerson) {
        return ResponseEntity.ok(peopleService.findPersonByCriteria(findPerson));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PeopleDto> setMailToPeople(@RequestBody MailToPeopleDto mailToPeopleDto) {
        PeopleDto people = peopleService.setMailToPeople(mailToPeopleDto.mailId(), mailToPeopleDto.peopleId());
        return new ResponseEntity<>(people, HttpStatus.OK);
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