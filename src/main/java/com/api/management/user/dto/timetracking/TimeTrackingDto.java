package com.api.management.user.dto.timetracking;

import com.api.management.user.dto.BaseDto;
import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.dto.task.TaskDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeTrackingDto extends BaseDto {

    @NotNull
    @Min(value = 0, message = "Time can't be negative")
    @Max(value = 10, message = "Value can't be greater than 10 digits")
    private Integer timeSpentMinutes;

    @Valid
    private TaskDto task;

    @Valid
    private EmployeeDto employee;
}
