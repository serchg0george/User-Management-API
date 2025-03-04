package com.teamsphere.mapper;

import com.teamsphere.dto.timesheet.TimesheetDto;
import com.teamsphere.entity.RoleEntity;
import com.teamsphere.entity.TimesheetEntity;
import com.teamsphere.exception.base.BaseNotFoundException;
import com.teamsphere.mapper.base.BaseMapper;
import com.teamsphere.repository.RoleRepository;
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
