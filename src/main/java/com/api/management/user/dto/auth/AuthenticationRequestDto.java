package com.api.management.user.dto.auth;

import jakarta.validation.constraints.Email;

public record AuthenticationRequestDto(@Email String email,
                                       String password) {

}