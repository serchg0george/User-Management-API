package com.teamsphere.service.impl;

import com.teamsphere.dto.timesheet.TimesheetDto;
import com.teamsphere.dto.timesheet.TimesheetSearchRequest;
import com.teamsphere.dto.timesheet.TimesheetSearchResponse;
import com.teamsphere.entity.TimesheetEntity;
import com.teamsphere.mapper.TimesheetMapper;
import com.teamsphere.mapper.base.BaseMapper;
import com.teamsphere.repository.TimesheetRepository;
import com.teamsphere.service.TimesheetService;
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
    public TimesheetSearchResponse findTimesheet(final TimesheetSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TimesheetEntity> criteriaQuery = criteriaBuilder.createQuery(TimesheetEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<TimesheetEntity> root = criteriaQuery.from(TimesheetEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate taskDescription = criteriaBuilder.like(root.get("taskDescription"), query);
            try {
                Integer timeSpentMinutesQuery = Integer.parseInt(request.query());
                Predicate timeSpentMinutes = criteriaBuilder.equal(root.get("timeSpentMinutes"), timeSpentMinutesQuery);
                predicates.add(criteriaBuilder.or(timeSpentMinutes, taskDescription));
            } catch (NumberFormatException e) {
                log.info("Query '{}' is not a valid number", e.getMessage());
            }
            predicates.add(taskDescription);
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<TimesheetEntity> query = entityManager.createQuery(criteriaQuery);

        TimesheetSearchResponse response = new TimesheetSearchResponse();

        var timeSheets = query.getResultList().stream().map(timeSheetMapper::mapEntityToDto).toList();

        response.setTimeSheets(timeSheets);
        response.setTimeSheetCount(timeSheets.size());

        log.debug("Found {} projects for query '{}'", response.getTimeSheetCount(), request.query());

        return response;
    }

}
