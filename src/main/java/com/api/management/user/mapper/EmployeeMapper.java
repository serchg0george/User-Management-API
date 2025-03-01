package com.api.management.user.mapper;

import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.entity.*;
import com.api.management.user.exception.base.BaseNotFoundException;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.DepartmentRepository;
import com.api.management.user.repository.PositionRepository;
import com.api.management.user.repository.ProjectRepository;
import com.api.management.user.repository.TimesheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeMapper implements BaseMapper<EmployeeEntity, EmployeeDto> {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final TimesheetRepository timesheetRepository;

    @Override
    public EmployeeDto mapEntityToDto(EmployeeEntity entity) {
        return EmployeeDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .pin(entity.getPin())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .departmentId(entity.getDepartment().getId())
                .positionId(entity.getPosition().getId())
                .timeSheetEmployeeId(entity.getTimeSheetEmployee().getId())
                .projectIds(entity.getProjects().stream()
                        .map(ProjectEntity::getId)
                        .toList())
                .build();
    }

    @Override
    public EmployeeEntity mapDtoToEntity(EmployeeDto dto) {
        List<ProjectEntity> projects = dto.getProjectIds().stream()
                .map(id -> projectRepository.findById(id).orElseThrow(() -> new BaseNotFoundException(id)))
                .toList();

        DepartmentEntity department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new BaseNotFoundException(dto.getDepartmentId()));

        PositionEntity position = positionRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new BaseNotFoundException(dto.getPositionId()));

        TimesheetEntity timesheet = timesheetRepository.findById(dto.getTimeSheetEmployeeId())
                .orElseThrow(() -> new BaseNotFoundException(dto.getTimeSheetEmployeeId()));

        return EmployeeEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .pin(dto.getPin())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .department(department)
                .position(position)
                .timeSheetEmployee(timesheet)
                .projects(projects)
                .build();
    }

}
