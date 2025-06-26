package com.ecommerce.service;

import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterRequest request) { //Service para registrar un nuevo usuario
        if (userRepository.findByEmail(request.email) != null) { //Verifica si el usuario ya existe
            throw new RuntimeException("El usuario ya existe");
        }
        User user = new User();
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setProvider("LOCAL");
        userRepository.save(user); //Guarda el usuario en la base de datos
        return jwtUtil.generateToken(user.getEmail()); //Genera un JWT para el usuario registrado
    }

    public String login(LoginRequest request) { //Service para iniciar sesión
        User user = userRepository.findByEmail(request.email);
        if (user == null || !passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return jwtUtil.generateToken(user.getEmail()); //Genera un JWT para el usuario autenticado
    }
}