package com.api.management.user.service;

import com.api.management.user.dto.timesheet.TimeSheetDto;
import com.api.management.user.dto.timesheet.TimeSheetSearchRequest;
import com.api.management.user.dto.timesheet.TimeSheetSearchResponse;
import com.api.management.user.entity.TimeSheetEntity;

public interface TimeSheetService extends GenericService<TimeSheetEntity, TimeSheetDto> {

    TimeSheetSearchResponse findTimeSheet(TimeSheetSearchRequest request);

}
