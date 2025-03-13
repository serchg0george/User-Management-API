package com.teamsphere.mapper;

import com.teamsphere.dto.employee.EmployeeDto;
import com.teamsphere.entity.*;
import com.teamsphere.exception.NotFoundException;
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
                .map(id -> projectRepository.findById(id).orElseThrow(() -> new NotFoundException(id)))
                .toList();

        DepartmentEntity department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(() -> new NotFoundException(dto.getDepartmentId()));

        PositionEntity position = positionRepository.findById(dto.getPositionId()).orElseThrow(() -> new NotFoundException(dto.getPositionId()));

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
                        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
                        task.setEmployee(employee);
                        return task;
                    })
                    .toList();
            employee.getTasks().addAll(tasks);
        }

        return employee;
    }

    @Override
    public void updateFromDto(EmployeeDto dto, EmployeeEntity entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPin(dto.getPin());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());

        if (dto.getDepartmentId() != null) {
            DepartmentEntity department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException(dto.getDepartmentId()));
            entity.setDepartment(department);
        }

        if (dto.getPositionId() != null) {
            PositionEntity position = positionRepository.findById(dto.getPositionId())
                    .orElseThrow(() -> new NotFoundException(dto.getPositionId()));
            entity.setPosition(position);
        }

        if (dto.getProjectIds() != null) {
            List<ProjectEntity> projects = dto.getProjectIds().stream()
                    .map(id -> projectRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException(id)))
                    .toList();
            entity.setProjects(projects);
        }

        if (dto.getTaskIds() != null) {
            List<TaskEntity> tasks = dto.getTaskIds().stream()
                    .map(id -> {
                        TaskEntity task = taskRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException(id));
                        task.setEmployee(entity);
                        return task;
                    })
                    .toList();
            entity.getTasks().clear();
            entity.getTasks().addAll(tasks);
        }
    }
}
