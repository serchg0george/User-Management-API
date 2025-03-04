package com.teamsphere.dto.role;

import com.teamsphere.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends BaseDto {

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20)
    private String roleName;

    @NotBlank
    @Size(min = 1, max = 150)
    private String description;

}
