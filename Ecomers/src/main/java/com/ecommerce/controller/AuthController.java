package com.ecommerce.controller;

import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) { //ResponseEnttity es una clase en SPring que representa respuesta HTTP que puede contener un cuerpo, encabezados y un código de estado
        String token = authService.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) { //@RequestBody LoginRequest request: El cuerpo de la solicitud se mapea al objeto LoginRequest, que contiene el email y la contraseña del usuario
        String token = authService.login(request); //Llama al servicio de autenticación (AuthService) para iniciar sesión
        return ResponseEntity.ok(token);  //devuleve Jwt como respuesta con código de estado 200 OK
    }
}