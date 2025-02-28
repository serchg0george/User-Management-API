package com.api.management.user.service;

import com.api.management.user.dto.timesheet.TimesheetDto;
import com.api.management.user.dto.timesheet.TimesheetSearchRequest;
import com.api.management.user.dto.timesheet.TimesheetSearchResponse;
import com.api.management.user.entity.TimesheetEntity;

public interface TimesheetService extends GenericService<TimesheetEntity, TimesheetDto> {

    TimesheetSearchResponse findTimeSheet(TimesheetSearchRequest request);

}
