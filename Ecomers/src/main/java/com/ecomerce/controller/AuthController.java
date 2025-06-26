package com.ecomerce.controller;

import com.ecomerce.dto.RegisterRequest;
import com.ecomerce.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // This annotation indicates that this class is a REST controller
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService; // This service handles authentication logic

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        String token = authService.registerUser(request);
        return ResponseEntity.ok(token);
    }
}