package com.api.management.user.mapper;

import com.api.management.user.dto.timesheet.TimesheetDto;
import com.api.management.user.entity.RoleEntity;
import com.api.management.user.entity.TimesheetEntity;
import com.api.management.user.exception.base.BaseNotFoundException;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimesheetMapper implements BaseMapper<TimesheetEntity, TimesheetDto> {

    private final RoleRepository roleRepository;

    @Override
    public TimesheetDto mapEntityToDto(TimesheetEntity entity) {
        return TimesheetDto.builder()
                .id(entity.getId())
                .timeSpentMinutes(entity.getTimeSpentMinutes())
                .taskDescription(entity.getTaskDescription())
                .roleId(entity.getRole().getId())
                .build();
    }

    @Override
    public TimesheetEntity mapDtoToEntity(TimesheetDto dto) {

        RoleEntity role = roleRepository.findById(dto.getRoleId()).orElseThrow(() -> new BaseNotFoundException(dto.getRoleId()));

        return TimesheetEntity.builder()
                .timeSpentMinutes(dto.getTimeSpentMinutes())
                .taskDescription(dto.getTaskDescription())
                .role(role)
                .build();
    }

}
