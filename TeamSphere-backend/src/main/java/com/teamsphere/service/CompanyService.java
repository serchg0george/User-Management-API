package com.teamsphere.service;

import com.teamsphere.dto.company.CompanyDto;
import com.teamsphere.dto.company.CompanySearchRequest;
import com.teamsphere.dto.company.CompanySearchResponse;
import com.teamsphere.entity.CompanyEntity;

public interface CompanyService extends GenericService<CompanyEntity, CompanyDto> {

    CompanySearchResponse findCompany(CompanySearchRequest request);

}
