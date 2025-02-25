package com.api.management.user.dto.auth;

import jakarta.validation.constraints.Email;

public record RegisterRequestDto(String firstName,
                                 String lastName,
                                 @Email String email,
                                 String password) {

}
