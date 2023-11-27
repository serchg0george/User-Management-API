package com.api.management.user.dto;

import jakarta.validation.constraints.Email;
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
public class MailDto extends BaseDto {

    @NotBlank
    @Size(max = 5)
    private String emailType;

    @Email
    @Size(max = 40)
    private String email;
}
