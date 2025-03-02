package com.api.management.user.service.impl;

import com.api.management.user.dto.position.PositionDto;
import com.api.management.user.dto.position.PositionSearchRequest;
import com.api.management.user.dto.position.PositionSearchResponse;
import com.api.management.user.entity.PositionEntity;
import com.api.management.user.mapper.PositionMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.PositionRepository;
import com.api.management.user.service.PositionService;
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
public class PositionServiceImpl extends GenericServiceImpl<PositionEntity, PositionDto> implements PositionService {

    private final PositionMapper positionMapper;
    private final PositionRepository positionRepository;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<PositionEntity, PositionDto> getMapper() {
        return positionMapper;
    }

    @Override
    public JpaRepository<PositionEntity, Long> getRepository() {
        return positionRepository;
    }

    @Override
    public PositionSearchResponse findPosition(final PositionSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PositionEntity> criteriaQuery = criteriaBuilder.createQuery(PositionEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<PositionEntity> root = criteriaQuery.from(PositionEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate roleName = criteriaBuilder.like(root.get("positionName"), query);

            try {
                Integer yearsOfExperienceQuery = Integer.parseInt(request.query());
                Predicate yearsOfExperience = criteriaBuilder.equal(root.get("yearsOfExperience"), yearsOfExperienceQuery);
                predicates.add(criteriaBuilder.or(roleName, yearsOfExperience));
            } catch (NumberFormatException e) {
                predicates.add(roleName);
            }

        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<PositionEntity> query = entityManager.createQuery(criteriaQuery);

        PositionSearchResponse response = new PositionSearchResponse();

        var positions = query.getResultList().stream().map(positionMapper::mapEntityToDto).toList();

        response.setPositions(positions);
        response.setPositionCount(positions.size());

        log.debug("Found {} projects for query '{}'", response.getPositionCount(), request.query());

        return response;
    }
}
