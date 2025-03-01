package com.api.management.user.dto.company;

import com.api.management.user.dto.BaseDto;
import jakarta.validation.constraints.Email;
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
public class CompanyDto extends BaseDto {

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    private String industry;

    @NotBlank
    @Size(min = 1, max = 50)
    private String address;

    @NotBlank
    @NotNull
    @Email
    @Size(min = 1, max = 50)
    private String email;

}
