package com.teamsphere.mapper;

import com.teamsphere.dto.employee.EmployeeDto;
import com.teamsphere.entity.*;
import com.teamsphere.exception.base.BaseNotFoundException;
import com.teamsphere.mapper.base.BaseMapper;
import com.teamsphere.repository.DepartmentRepository;
import com.teamsphere.repository.PositionRepository;
import com.teamsphere.repository.ProjectRepository;
import com.teamsphere.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeMapper implements BaseMapper<EmployeeEntity, EmployeeDto> {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final TaskRepository taskRepository;

    @Override
    public EmployeeDto toDto(EmployeeEntity entity) {
        return EmployeeDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .pin(entity.getPin())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .departmentId(entity.getDepartment().getId())
                .positionId(entity.getPosition().getId())
                .taskIds(entity.getTasks().stream()
                        .map(TaskEntity::getId)
                        .toList())
                .projectIds(entity.getProjects().stream()
                        .map(ProjectEntity::getId)
                        .toList())
                .build();
    }

    @Override
    public EmployeeEntity toEntity(EmployeeDto dto) {
        List<ProjectEntity> projects = dto.getProjectIds().stream()
                .map(id -> projectRepository.findById(id).orElseThrow(() -> new BaseNotFoundException(id)))
                .toList();

        DepartmentEntity department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(() -> new BaseNotFoundException(dto.getDepartmentId()));

        PositionEntity position = positionRepository.findById(dto.getPositionId()).orElseThrow(() -> new BaseNotFoundException(dto.getPositionId()));

        EmployeeEntity employee = EmployeeEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .pin(dto.getPin())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .department(department)
                .position(position)
                .projects(projects)
                .tasks(new ArrayList<>())
                .build();

        if (dto.getTaskIds() != null) {
            List<TaskEntity> tasks = dto.getTaskIds().stream()
                    .map(id -> {
                        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> new BaseNotFoundException(id));
                        task.setEmployee(employee);
                        return task;
                    })
                    .toList();
            employee.getTasks().addAll(tasks);
        }

        return employee;
    }

}
