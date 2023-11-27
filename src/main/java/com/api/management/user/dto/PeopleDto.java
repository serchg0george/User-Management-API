package com.api.management.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class PeopleDto extends BaseDto {

    @NotBlank
    @Size(max = 90)
    @Pattern(regexp = "^[a-zA-Z\\p{IsCyrillic} -]+$")
    private String fullName;

    @Pattern(regexp = "^(?!\\s*$)[-0-9\\s]{10}$")
    private String pin;

    @Valid
    AddressDto address;

    @Valid
    List<MailDto> mails;

}