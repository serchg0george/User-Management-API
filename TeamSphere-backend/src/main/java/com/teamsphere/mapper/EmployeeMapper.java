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
import java.util.Collections;
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
        EmployeeEntity employee = buildBasicEmployee(dto);
        employee.setProjects(getProjectsByIds(dto.getProjectIds()));
        employee.setTasks(getTasksByIds(dto.getTaskIds(), employee));
        return employee;
    }

    @Override
    public void updateFromDto(EmployeeDto dto, EmployeeEntity entity) {
        updateBasicFields(dto, entity);
        updateDepartment(dto, entity);
        updatePosition(dto, entity);
        updateProjects(dto, entity);
        updateTasks(dto, entity);
    }

    private EmployeeEntity buildBasicEmployee(EmployeeDto dto) {
        return EmployeeEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .pin(dto.getPin())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .department(findDepartmentById(dto.getDepartmentId()))
                .position(findPositionById(dto.getPositionId()))
                .projects(new ArrayList<>())
                .tasks(new ArrayList<>())
                .build();
    }

    private List<TaskEntity> getTasksByIds(List<Long> taskIds, EmployeeEntity employee) {
        if (taskIds == null) return Collections.emptyList();
        return taskIds.stream()
                .map(id -> {
                    TaskEntity task = findTaskById(id);
                    task.setEmployee(employee);
                    return task;
                })
                .toList();
    }

    private void updateBasicFields(EmployeeDto dto, EmployeeEntity entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPin(dto.getPin());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
    }

    private void updateDepartment(EmployeeDto dto, EmployeeEntity entity) {
        if (dto.getDepartmentId() != null) {
            entity.setDepartment(findDepartmentById(dto.getDepartmentId()));
        }
    }

    private void updatePosition(EmployeeDto dto, EmployeeEntity entity) {
        if (dto.getPositionId() != null) {
            entity.setPosition(findPositionById(dto.getPositionId()));
        }
    }

    private void updateProjects(EmployeeDto dto, EmployeeEntity entity) {
        if (dto.getProjectIds() != null) {
            entity.getProjects().clear();
            entity.getProjects().addAll(getProjectsByIds(dto.getProjectIds()));
        }
    }

    private void updateTasks(EmployeeDto dto, EmployeeEntity entity) {
        if (dto.getTaskIds() == null) return;

        List<Long> existingTaskIds = entity.getTasks().stream()
                .map(TaskEntity::getId)
                .toList();

        entity.getTasks().removeIf(task -> !dto.getTaskIds().contains(task.getId()));

        for (Long taskId : dto.getTaskIds()) {
            if (!existingTaskIds.contains(taskId)) {
                TaskEntity task = findTaskById(taskId);
                task.setEmployee(entity);
                entity.getTasks().add(task);
            }
        }
    }

    private List<ProjectEntity> getProjectsByIds(List<Long> ids) {
        return ids != null
                ? ids.stream().map(this::findProjectById).toList()
                : Collections.emptyList();
    }

    private ProjectEntity findProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    private DepartmentEntity findDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    private PositionEntity findPositionById(Long id) {
        return positionRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    private TaskEntity findTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

}
