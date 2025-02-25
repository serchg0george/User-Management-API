package com.api.management.user.service.impl;

import com.api.management.user.dto.PeopleDto;
import com.api.management.user.dto.search.PeopleSearchRequest;
import com.api.management.user.entity.MailEntity;
import com.api.management.user.entity.PeopleEntity;
import com.api.management.user.exception.NotFoundException;
import com.api.management.user.mapper.PeopleMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.MailRepository;
import com.api.management.user.repository.PeopleRepository;
import com.api.management.user.service.PeopleService;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class PeopleServiceImpl extends GenericServiceImpl<PeopleEntity, PeopleDto> implements PeopleService {

    private final PeopleRepository peopleRepository;
    private final MailRepository mailRepository;
    private final PeopleMapper peopleMapper;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<PeopleEntity, PeopleDto> getMapper() {
        return peopleMapper;
    }

    @Override
    public JpaRepository<PeopleEntity, Long> getRepository() {
        return peopleRepository;
    }

    @Override
    public PeopleDto setMailToPeople(Long mailId, Long peopleId) {
        MailEntity mailEntity = mailRepository.findById(mailId).orElseThrow(
                () -> new NotFoundException(mailId));
        PeopleEntity peopleEntity = peopleRepository.findById(peopleId).orElseThrow(
                () -> new NotFoundException(peopleId));

        mailEntity.setPeople(peopleEntity);
        mailRepository.save(mailEntity);
        Optional<PeopleEntity> people = peopleRepository.findById(peopleId);
        if (people.isEmpty()) {
            throw new NotFoundException(peopleId);
        }
        return peopleMapper.mapEntityToDto(people.get());
    }

    @Override
    public List<PeopleDto> findPersonByCriteria(final PeopleSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PeopleEntity> criteriaQuery = criteriaBuilder.createQuery(PeopleEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<PeopleEntity> root = criteriaQuery.from(PeopleEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate name = criteriaBuilder.like(root.get("full_name"), query);
            Predicate pin = criteriaBuilder.like(root.get("pin"), query);

            predicates.add(criteriaBuilder.or(name, pin));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<PeopleEntity> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList().stream().map(peopleMapper::mapEntityToDto).toList();
    }
}
