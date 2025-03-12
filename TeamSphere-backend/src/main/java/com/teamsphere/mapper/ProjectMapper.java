package com.teamsphere.mapper;

import com.teamsphere.dto.project.ProjectDto;
import com.teamsphere.entity.CompanyEntity;
import com.teamsphere.entity.ProjectEntity;
import com.teamsphere.entity.enums.ProjectStatus;
import com.teamsphere.exception.base.BaseNotFoundException;
import com.teamsphere.mapper.base.BaseMapper;
import com.teamsphere.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ProjectMapper implements BaseMapper<ProjectEntity, ProjectDto> {

    private final CompanyRepository companyRepository;

    public ProjectDto toDto(ProjectEntity entity) {

        LocalDate finishDateEntity = entity.getFinishDate();
        String finishDate = null;

        if (finishDateEntity != null) {
            finishDate = finishDateEntity.toString();
        }

        return ProjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .startDate(entity.getStartDate().toString())
                .finishDate(finishDate)
                .status(entity.getStatus().toString())
                .companyId(entity.getCompany().getId())
                .build();
    }

    public ProjectEntity toEntity(ProjectDto dto) {
        CompanyEntity company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new BaseNotFoundException(dto.getCompanyId()));

        return new ProjectEntity(
                dto.getName(),
                dto.getDescription(),
                LocalDate.parse(dto.getStartDate()),
                LocalDate.parse(dto.getFinishDate()),
                ProjectStatus.valueOf(dto.getStatus()),
                company
        );
    }
}
