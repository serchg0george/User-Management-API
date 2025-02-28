package com.api.management.user.service.impl;

import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.dto.employee.SearchEmployeeResponse;
import com.api.management.user.dto.search.PeopleSearchRequest;
import com.api.management.user.entity.EmployeeEntity;
import com.api.management.user.mapper.EmployeeMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.EmployeeRepository;
import com.api.management.user.service.EmployeeService;
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
public class EmployeeServiceImpl extends GenericServiceImpl<EmployeeEntity, EmployeeDto> implements EmployeeService {

    private final EmployeeRepository peopleRepository;
    private final EmployeeMapper employeeMapper;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<EmployeeEntity, EmployeeDto> getMapper() {
        return employeeMapper;
    }

    @Override
    public JpaRepository<EmployeeEntity, Long> getRepository() {
        return peopleRepository;
    }


    @Override
    public SearchEmployeeResponse findEmployee(final PeopleSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> criteriaQuery = criteriaBuilder.createQuery(EmployeeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<EmployeeEntity> root = criteriaQuery.from(EmployeeEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate name = criteriaBuilder.like(root.get("full_name"), query);
            Predicate pin = criteriaBuilder.like(root.get("pin"), query);

            predicates.add(criteriaBuilder.or(name, pin));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<EmployeeEntity> query = entityManager.createQuery(criteriaQuery);

        SearchEmployeeResponse response = new SearchEmployeeResponse();

        var people = query.getResultList().stream().map(employeeMapper::mapEntityToDto).toList();

        response.setPeople(people);
        response.setEmployeeCount(people.size());

        return response;
    }
}
