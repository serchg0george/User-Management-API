package com.api.management.user.service.impl;

import com.api.management.user.dto.department.DepartmentDto;
import com.api.management.user.dto.department.DepartmentSearchRequest;
import com.api.management.user.dto.department.DepartmentSearchResponse;
import com.api.management.user.entity.DepartmentEntity;
import com.api.management.user.mapper.DepartmentMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.DepartmentRepository;
import com.api.management.user.service.DepartmentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DepartmentServiceImpl extends GenericServiceImpl<DepartmentEntity, DepartmentDto> implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<DepartmentEntity, DepartmentDto> getMapper() {
        return departmentMapper;
    }

    @Override
    public JpaRepository<DepartmentEntity, Long> getRepository() {
        return departmentRepository;
    }

    @Override
    public DepartmentSearchResponse findDepartment(final DepartmentSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DepartmentEntity> criteriaQuery = criteriaBuilder.createQuery(DepartmentEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<DepartmentEntity> root = criteriaQuery.from(DepartmentEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate groupName = criteriaBuilder.like(root.get("groupName"), query);
            Predicate description = criteriaBuilder.like(root.get("description"), query);

            predicates.add(criteriaBuilder.or(groupName, description));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<DepartmentEntity> query = entityManager.createQuery(criteriaQuery);

        DepartmentSearchResponse response = new DepartmentSearchResponse();

        var departments = query.getResultList().stream().map(departmentMapper::mapEntityToDto).toList();

        response.setDepartments(departments);
        response.setDepartmentCount(departments.size());

        log.debug("Found {} projects for query '{}'", response.getDepartmentCount(), request.query());

        return response;
    }
}
