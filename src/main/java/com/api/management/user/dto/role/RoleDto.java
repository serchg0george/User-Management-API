package com.api.management.user.dto.role;

import com.api.management.user.dto.BaseDto;
import com.api.management.user.dto.timesheet.TimeSheetDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RoleDto extends BaseDto {

    @NotBlank
    @Size(min = 1, max = 20)
    private String roleName;

    @NotBlank
    @Size(min = 1, max = 150)
    private String description;

    @Valid
    private List<TimeSheetDto> timeSheets;


}
