package com.api.management.user.service.impl;

import com.api.management.user.dto.mail.MailDto;
import com.api.management.user.dto.mail.SearchMailResponse;
import com.api.management.user.dto.search.MailSearchRequest;
import com.api.management.user.entity.EmailType;
import com.api.management.user.entity.MailEntity;
import com.api.management.user.mapper.MailMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.MailRepository;
import com.api.management.user.service.MailService;
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
public class MailServiceImpl extends GenericServiceImpl<MailEntity, MailDto> implements MailService {

    private final MailRepository mailRepository;
    private final MailMapper mailMapper;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<MailEntity, MailDto> getMapper() {
        return mailMapper;
    }

    @Override
    public JpaRepository<MailEntity, Long> getRepository() {
        return mailRepository;
    }

    @Override
    public SearchMailResponse findMailByCriteria(final MailSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MailEntity> criteriaQuery = criteriaBuilder.createQuery(MailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<MailEntity> root = criteriaQuery.from(MailEntity.class);

        if (request.query() != null && !request.query().isBlank()) {

            String query = "%" + request.query() + "%";
            Predicate email = criteriaBuilder.like(root.get("email"), query);

            EmailType emailType = EmailType.valueOf(request.query().toUpperCase());
            Predicate emailTypePredicate = criteriaBuilder.equal(root.get("emailType"), emailType);

            predicates.add(criteriaBuilder.or(emailTypePredicate, email));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<MailEntity> query = entityManager.createQuery(criteriaQuery);

        final SearchMailResponse response = new SearchMailResponse();

        var mailAddresses = query.getResultList().stream().map(mailMapper::mapEntityToDto).toList();

        response.setMails(mailAddresses);
        response.setMailsCount(mailAddresses.size());

        return response;
    }
}
