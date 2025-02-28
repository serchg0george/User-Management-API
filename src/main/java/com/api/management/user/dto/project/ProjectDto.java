package com.api.management.user.dto.project;

import com.api.management.user.dto.BaseDto;
import com.api.management.user.dto.company.CompanyDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto extends BaseDto {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Size(min = 1, max = 500)
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date finishDate;

    @NotBlank
    private String status;

    @Valid
    private CompanyDto organization;
}
