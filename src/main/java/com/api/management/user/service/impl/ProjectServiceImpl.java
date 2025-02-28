package com.api.management.user.service.impl;

import com.api.management.user.dto.project.ProjectDto;
import com.api.management.user.dto.project.ProjectSearchRequest;
import com.api.management.user.dto.project.ProjectSearchResponse;
import com.api.management.user.entity.ProjectEntity;
import com.api.management.user.entity.enums.ProjectStatus;
import com.api.management.user.mapper.ProjectMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.ProjectRepository;
import com.api.management.user.service.ProjectService;
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
public class ProjectServiceImpl extends GenericServiceImpl<ProjectEntity, ProjectDto> implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<ProjectEntity, ProjectDto> getMapper() {
        return projectMapper;
    }

    @Override
    public JpaRepository<ProjectEntity, Long> getRepository() {
        return projectRepository;
    }

    @Override
    public ProjectSearchResponse findProject(final ProjectSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProjectEntity> criteriaQuery = criteriaBuilder.createQuery(ProjectEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<ProjectEntity> root = criteriaQuery.from(ProjectEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate name = criteriaBuilder.like(root.get("name"), query);
            Predicate description = criteriaBuilder.like(root.get("description"), query);
            Predicate startDate = criteriaBuilder.like(root.get("start_date"), query);
            Predicate finishDate = criteriaBuilder.like(root.get("finish_date"), query);
            ProjectStatus projectStatus = ProjectStatus.valueOf(request.query().toUpperCase());
            Predicate status = criteriaBuilder.equal(root.get("status"), projectStatus);

            predicates.add(criteriaBuilder.or(name, description, startDate, finishDate, status));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<ProjectEntity> query = entityManager.createQuery(criteriaQuery);

        ProjectSearchResponse response = new ProjectSearchResponse();

        var projects = query.getResultList().stream().map(projectMapper::mapEntityToDto).toList();

        response.setProjects(projects);
        response.setProjectCount(projects.size());

        return response;
    }
}
