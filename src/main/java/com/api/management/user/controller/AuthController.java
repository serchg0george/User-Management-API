package com.api.management.user.controller;

import com.api.management.user.auth.AuthenticationResponse;
import com.api.management.user.auth.AuthenticationService;
import com.api.management.user.dto.auth.AuthenticationRequestDto;
import com.api.management.user.dto.auth.RegisterRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
@Validated
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequestDto request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequestDto request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
