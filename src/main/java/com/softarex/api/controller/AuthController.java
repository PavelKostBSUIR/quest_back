package com.softarex.api.controller;

import com.softarex.api.entity.dto.JwtRequest;
import com.softarex.api.entity.dto.JwtResponse;
import com.softarex.api.entity.dto.RefreshJwtRequest;
import com.softarex.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("login")
    public JwtResponse login(@RequestBody JwtRequest authRequest) {
        JwtResponse token = authService.login(authRequest);
        return token;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("refresh")
    public JwtResponse getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return token;
    }
}