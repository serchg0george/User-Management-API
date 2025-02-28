package com.api.management.user.dto.project;

import com.api.management.user.dto.BaseDto;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Id cannot be null")
    @Positive(message = "Id must be a positive number")
    @Min(value = 1, message = "Id cannot be lower than 1")
    private Long companyId;
}
