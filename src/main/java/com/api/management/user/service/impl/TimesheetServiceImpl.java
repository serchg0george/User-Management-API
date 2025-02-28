package com.api.management.user.service.impl;

import com.api.management.user.dto.timesheet.TimesheetDto;
import com.api.management.user.dto.timesheet.TimesheetSearchRequest;
import com.api.management.user.dto.timesheet.TimesheetSearchResponse;
import com.api.management.user.entity.TimesheetEntity;
import com.api.management.user.mapper.TimesheetMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.TimesheetRepository;
import com.api.management.user.service.TimesheetService;
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
public class TimesheetServiceImpl extends GenericServiceImpl<TimesheetEntity, TimesheetDto> implements TimesheetService {

    private final TimesheetMapper timeSheetMapper;
    private final TimesheetRepository timeSheetRepository;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<TimesheetEntity, TimesheetDto> getMapper() {
        return timeSheetMapper;
    }

    @Override
    public JpaRepository<TimesheetEntity, Long> getRepository() {
        return timeSheetRepository;
    }

    @Override
    public TimesheetSearchResponse findTimeSheet(final TimesheetSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TimesheetEntity> criteriaQuery = criteriaBuilder.createQuery(TimesheetEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<TimesheetEntity> root = criteriaQuery.from(TimesheetEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate timeSpentMinutes = criteriaBuilder.like(root.get("time_spent_minutes"), query);
            Predicate taskDescription = criteriaBuilder.like(root.get("task_description"), query);
            predicates.add(criteriaBuilder.or(timeSpentMinutes, taskDescription));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<TimesheetEntity> query = entityManager.createQuery(criteriaQuery);

        TimesheetSearchResponse response = new TimesheetSearchResponse();

        var timeSheets = query.getResultList().stream().map(timeSheetMapper::mapEntityToDto).toList();

        response.setTimeSheets(timeSheets);
        response.setTimeSheetCount(timeSheets.size());

        return response;
    }
}
