package com.teamsphere.dto.department;

import com.teamsphere.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto extends BaseDto {

    @NotBlank
    @NotNull
    @Size(min = 1, max = 40)
    private String groupName;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 100)
    private String description;

}
