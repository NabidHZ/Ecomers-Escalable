package com.ecomers.controller;

import com.ecomers.dto.RegisterRequest;
import com.ecomers.service.AuthService;
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

    @PostMapping("/register") // This endpoint handles user registration
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {  // Validates the request body and maps it to RegisterRequest
        authService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }
}