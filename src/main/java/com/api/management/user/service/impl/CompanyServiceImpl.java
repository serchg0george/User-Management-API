package com.api.management.user.service.impl;

import com.api.management.user.dto.company.CompanyDto;
import com.api.management.user.dto.company.CompanySearchRequest;
import com.api.management.user.dto.company.CompanySearchResponse;
import com.api.management.user.entity.CompanyEntity;
import com.api.management.user.mapper.CompanyMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.CompanyRepository;
import com.api.management.user.service.CompanyService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl extends GenericServiceImpl<CompanyEntity, CompanyDto> implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<CompanyEntity, CompanyDto> getMapper() {
        return companyMapper;
    }

    @Override
    public JpaRepository<CompanyEntity, Long> getRepository() {
        return companyRepository;
    }

    @Override
    public CompanySearchResponse findCompany(final CompanySearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompanyEntity> criteriaQuery = criteriaBuilder.createQuery(CompanyEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate name = criteriaBuilder.like(root.get("name"), query);
            Predicate industry = criteriaBuilder.like(root.get("industry"), query);
            Predicate address = criteriaBuilder.like(root.get("address"), query);
            Predicate email = criteriaBuilder.like(root.get("email"), query);

            predicates.add(criteriaBuilder.or(name, industry, address, email));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<CompanyEntity> query = entityManager.createQuery(criteriaQuery);

        CompanySearchResponse response = new CompanySearchResponse();

        var companies = query.getResultList().stream().map(companyMapper::mapEntityToDto).toList();

        response.setCompanies(companies);
        response.setCompanyCount(companies.size());

        return response;
    }
}
