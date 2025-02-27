package com.api.management.user.dto.company;

import com.api.management.user.dto.BaseDto;
import com.api.management.user.dto.project.ProjectDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class CompanyDto extends BaseDto {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    private String industry;

    @NotBlank
    @Size(min = 1, max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 1, max = 50)
    private String address;

    @Valid
    private List<ProjectDto> projects;
}
