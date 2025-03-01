package com.api.management.user.dto.position;

import com.api.management.user.dto.BaseDto;
import jakarta.validation.constraints.Min;
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
public class PositionDto extends BaseDto {

    @NotBlank(message = "Should not be blank")
    @NotNull
    @Size(min = 1, max = 50)
    private String positionName;

    @NotNull(message = "Must not be null")
    @Min(value = 0, message = "Can't be negative number")
    private Integer yearsOfExperience;

}
