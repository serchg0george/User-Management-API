package com.api.management.user.controller;

import com.api.management.user.dto.MailDto;
import com.api.management.user.service.MailService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.api.management.user.controller.Constants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/mail")
@Validated
public class MailController {

    private final MailService mailService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createMail(@Valid @RequestBody MailDto mail) {
        mailService.save(mail);
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<MailDto> getMailById(@PathVariable("id") Long mailId) {
        return new ResponseEntity<>(mailService.get(mailId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<MailDto>> getAllMail(Pageable pageable) {
        return new ResponseEntity<>(mailService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateMail(@PathVariable("id") Long mailId,
                                             @Valid @RequestBody MailDto mail) {
        mailService.update(mail, mailId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMail(@PathVariable("id") Long mailId) {
        mailService.delete(mailId);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }
}