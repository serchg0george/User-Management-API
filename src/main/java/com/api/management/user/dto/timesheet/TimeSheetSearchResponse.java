package com.api.management.user.dto.timesheet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetSearchResponse {

    private List<TimeSheetDto> timeSheets;

    private Integer timeSheetCount;

}
