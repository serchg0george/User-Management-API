package com.api.management.user.service.impl;

import com.api.management.user.dto.company.CompanyDto;
import com.api.management.user.entity.CompanyEntity;
import com.api.management.user.mapper.CompanyMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl extends GenericServiceImpl<CompanyEntity, CompanyDto> {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public BaseMapper<CompanyEntity, CompanyDto> getMapper() {
        return companyMapper;
    }

    @Override
    public JpaRepository<CompanyEntity, Long> getRepository() {
        return companyRepository;
    }
}
