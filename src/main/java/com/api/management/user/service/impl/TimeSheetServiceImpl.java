package com.api.management.user.service.impl;

import com.api.management.user.dto.timesheet.TimeSheetDto;
import com.api.management.user.entity.TimeSheetEntity;
import com.api.management.user.mapper.TimeSheetMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.TimeSheetRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TimeSheetServiceImpl extends GenericServiceImpl<TimeSheetEntity, TimeSheetDto> {

    private final TimeSheetMapper timeSheetMapper;
    private final TimeSheetRepository timeSheetRepository;

    @Override
    public BaseMapper<TimeSheetEntity, TimeSheetDto> getMapper() {
        return timeSheetMapper;
    }

    @Override
    public JpaRepository<TimeSheetEntity, Long> getRepository() {
        return timeSheetRepository;
    }
}
