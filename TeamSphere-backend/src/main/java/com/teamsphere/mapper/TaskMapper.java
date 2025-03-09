package com.teamsphere.mapper;

import com.teamsphere.dto.task.TaskDto;
import com.teamsphere.entity.RoleEntity;
import com.teamsphere.entity.TaskEntity;
import com.teamsphere.exception.base.BaseNotFoundException;
import com.teamsphere.mapper.base.BaseMapper;
import com.teamsphere.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper implements BaseMapper<TaskEntity, TaskDto> {

    private final RoleRepository roleRepository;

    @Override
    public TaskDto mapEntityToDto(TaskEntity entity) {
        return TaskDto.builder()
                .id(entity.getId())
                .timeSpentMinutes(entity.getTimeSpentMinutes())
                .taskDescription(entity.getTaskDescription())
                .roleId(entity.getRole().getId())
                .build();
    }

    @Override
    public TaskEntity mapDtoToEntity(TaskDto dto) {

        RoleEntity role = roleRepository.findById(dto.getRoleId()).orElseThrow(() -> new BaseNotFoundException(dto.getRoleId()));

        return TaskEntity.builder()
                .timeSpentMinutes(dto.getTimeSpentMinutes())
                .taskDescription(dto.getTaskDescription())
                .role(role)
                .build();
    }

}
