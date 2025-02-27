package com.api.management.user.dto.employee;

import com.api.management.user.dto.BaseDto;
import com.api.management.user.dto.department.DepartmentDto;
import com.api.management.user.dto.position.PositionDto;
import com.api.management.user.dto.project.ProjectDto;
import com.api.management.user.dto.task.TaskDto;
import com.api.management.user.dto.timesheet.TimeSheetDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends BaseDto {

    @NotBlank
    @Size(max = 90)
    @Pattern(regexp = "^[a-zA-Z\\p{IsCyrillic} -]+$")
    private String fullName;

    @Pattern(regexp = "^(?!\\s*$)[-0-9\\s]{10}$")
    private String pin;

    @NotBlank
    @Email
    @Size(min = 1, max = 50)
    private String email;

    @NotBlank
    @Size(min = 1, max = 50)
    private String address;

    @Valid
    private DepartmentDto department;

    @Valid
    private PositionDto position;

    @Valid
    private TimeSheetDto timeSpentMinutes;

    @Valid
    private List<TaskDto> tasks;

    @Valid
    private List<ProjectDto> projects;

}