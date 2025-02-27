package com.api.management.user.dto.task;

import com.api.management.user.dto.BaseDto;
import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.dto.timesheet.TimeSheetDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends BaseDto {

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @NotBlank
    @Size(min = 1, max = 400)
    private String description;

    @NotBlank
    @Size(min = 1, max = 10)
    private Integer estimatedTimeMinutes;

    @NotBlank
    @Size(min = 1, max = 50)
    private String status;

    @Valid
    private TimeSheetDto timeSpentMinutes;

    @Valid
    private EmployeeDto assignedToEmployee;
}
