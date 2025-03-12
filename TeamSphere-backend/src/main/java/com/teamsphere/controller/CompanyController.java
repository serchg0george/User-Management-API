package com.teamsphere.controller;

import com.teamsphere.dto.company.CompanyDto;
import com.teamsphere.dto.company.CompanySearchRequest;
import com.teamsphere.dto.company.CompanySearchResponse;
import com.teamsphere.exception.NotFoundException;
import com.teamsphere.service.CompanyService;
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
        return ResponseEntity.ok(CREATE_SUCCESS);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") Long companyId) {
        return ResponseEntity.ok(companyService.get(companyId));
    }

    @GetMapping
    public ResponseEntity<Page<CompanyDto>> getAllCompanies(Pageable pageable) {
        return ResponseEntity.ok(companyService.getAll(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCompany(@PathVariable("id") Long companyId,
                                                @Valid @RequestBody CompanyDto company) {
        companyService.update(company, companyId);
        return ResponseEntity.ok(UPDATE_SUCCESS);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long companyId) {
        try {
            companyService.delete(companyId);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
