package com.api.management.user.dto.timesheet;

import com.api.management.user.dto.BaseDto;
import com.api.management.user.dto.employee.EmployeeDto;
import com.api.management.user.dto.role.RoleDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetDto extends BaseDto {

    @NotNull
    @Min(value = 0, message = "Time can't be negative")
    @Max(value = 10, message = "Value can't be greater than 10 digits")
    private Integer timeSpentMinutes;

    @NotBlank
    @Size(min = 1, max = 150)
    private String taskDescription;

    @Valid
    private RoleDto role;

    @Valid
    private EmployeeDto employee;
}
