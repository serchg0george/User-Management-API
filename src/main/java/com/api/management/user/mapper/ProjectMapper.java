package com.api.management.user.mapper;

import com.api.management.user.dto.project.ProjectDto;
import com.api.management.user.entity.CompanyEntity;
import com.api.management.user.entity.ProjectEntity;
import com.api.management.user.entity.enums.ProjectStatus;
import com.api.management.user.exception.base.BaseNotFoundException;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ProjectMapper implements BaseMapper<ProjectEntity, ProjectDto> {

    private final CompanyRepository companyRepository;

    public ProjectDto mapEntityToDto(ProjectEntity entity) {
        return ProjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .startDate(entity.getStartDate().toString())
                .finishDate(entity.getFinishDate().toString())
                .status(entity.getStatus().toString())
                .companyId(entity.getCompany().getId())
                .build();
    }

    public ProjectEntity mapDtoToEntity(ProjectDto dto) {
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
