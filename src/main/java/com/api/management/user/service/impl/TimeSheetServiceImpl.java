package com.api.management.user.service.impl;

import com.api.management.user.dto.timesheet.TimeSheetDto;
import com.api.management.user.dto.timesheet.TimeSheetSearchRequest;
import com.api.management.user.dto.timesheet.TimeSheetSearchResponse;
import com.api.management.user.entity.TimeSheetEntity;
import com.api.management.user.mapper.TimeSheetMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.TimeSheetRepository;
import com.api.management.user.service.TimeSheetService;
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
public class TimeSheetServiceImpl extends GenericServiceImpl<TimeSheetEntity, TimeSheetDto> implements TimeSheetService {

    private final TimeSheetMapper timeSheetMapper;
    private final TimeSheetRepository timeSheetRepository;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<TimeSheetEntity, TimeSheetDto> getMapper() {
        return timeSheetMapper;
    }

    @Override
    public JpaRepository<TimeSheetEntity, Long> getRepository() {
        return timeSheetRepository;
    }

    @Override
    public TimeSheetSearchResponse findTimeSheet(final TimeSheetSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TimeSheetEntity> criteriaQuery = criteriaBuilder.createQuery(TimeSheetEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<TimeSheetEntity> root = criteriaQuery.from(TimeSheetEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate timeSpentMinutes = criteriaBuilder.like(root.get("time_spent_minutes"), query);
            Predicate taskDescription = criteriaBuilder.like(root.get("task_description"), query);
            predicates.add(criteriaBuilder.or(timeSpentMinutes, taskDescription));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<TimeSheetEntity> query = entityManager.createQuery(criteriaQuery);

        TimeSheetSearchResponse response = new TimeSheetSearchResponse();

        var timeSheets = query.getResultList().stream().map(timeSheetMapper::mapEntityToDto).toList();

        response.setTimeSheets(timeSheets);
        response.setTimeSheetCount(timeSheets.size());

        return response;
    }
}
