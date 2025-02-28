package com.api.management.user.service.impl;

import com.api.management.user.dto.role.RoleDto;
import com.api.management.user.dto.role.RoleSearchRequest;
import com.api.management.user.dto.role.RoleSearchResponse;
import com.api.management.user.entity.RoleEntity;
import com.api.management.user.mapper.RoleMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.RoleRepository;
import com.api.management.user.service.RoleService;
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
public class RoleServiceImpl extends GenericServiceImpl<RoleEntity, RoleDto> implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<RoleEntity, RoleDto> getMapper() {
        return roleMapper;
    }

    @Override
    public JpaRepository<RoleEntity, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public RoleSearchResponse findRole(final RoleSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RoleEntity> criteriaQuery = criteriaBuilder.createQuery(RoleEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<RoleEntity> root = criteriaQuery.from(RoleEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate roleName = criteriaBuilder.like(root.get("role_name"), query);
            Predicate description = criteriaBuilder.like(root.get("description"), query);

            predicates.add(criteriaBuilder.and(roleName, description));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<RoleEntity> query = entityManager.createQuery(criteriaQuery);

        RoleSearchResponse response = new RoleSearchResponse();

        var roles = query.getResultList().stream().map(roleMapper::mapEntityToDto).toList();

        response.setRoles(roles);
        response.setRoleCount(roles.size());

        return response;
    }
}
