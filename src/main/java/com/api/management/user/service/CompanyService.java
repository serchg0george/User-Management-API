package com.api.management.user.service;

import com.api.management.user.dto.company.CompanyDto;
import com.api.management.user.dto.company.CompanySearchRequest;
import com.api.management.user.dto.company.CompanySearchResponse;
import com.api.management.user.entity.CompanyEntity;

public interface CompanyService extends GenericService<CompanyEntity, CompanyDto> {

    CompanySearchResponse findCompany(CompanySearchRequest request);

}
