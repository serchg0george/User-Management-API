package com.teamsphere.service;

import com.teamsphere.dto.timesheet.TimesheetDto;
import com.teamsphere.dto.timesheet.TimesheetSearchRequest;
import com.teamsphere.dto.timesheet.TimesheetSearchResponse;
import com.teamsphere.entity.TimesheetEntity;

public interface TimesheetService extends GenericService<TimesheetEntity, TimesheetDto> {

    TimesheetSearchResponse findTimesheet(TimesheetSearchRequest request);

}
