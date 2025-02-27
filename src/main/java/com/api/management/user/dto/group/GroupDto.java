package com.api.management.user.dto.group;

import com.api.management.user.dto.BaseDto;
import com.api.management.user.dto.employee.EmployeeDto;
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
public class GroupDto extends BaseDto {

    @NotBlank
    @Size(min = 1, max = 40)
    private String groupName;

    @NotBlank
    @Size(min = 1, max = 100)
    private String description;

    @Valid
    private EmployeeDto employee;
}
