package com.teamsphere.controller;

import com.teamsphere.dto.company.CompanyDto;
import com.teamsphere.dto.company.CompanySearchRequest;
import com.teamsphere.dto.company.CompanySearchResponse;
import com.teamsphere.service.CompanyService;
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
@RequestMapping("api/v1/company")
@Validated
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/search")
    public ResponseEntity<CompanySearchResponse> searchCompany(@RequestBody CompanySearchRequest findCompany) {
        return ResponseEntity.ok(companyService.findCompany(findCompany));
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@Valid @RequestBody CompanyDto company) {
        companyService.save(company);
        return ok().body(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") Long companyId) {
        return new ResponseEntity<>(companyService.get(companyId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CompanyDto>> getAllCompanies(Pageable pageable) {
        return new ResponseEntity<>(companyService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCompany(@PathVariable("id") Long companyId,
                                                @Valid @RequestBody CompanyDto company) {
        companyService.update(company, companyId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long companyId) {
        companyService.delete(companyId);
        return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
    }
}
