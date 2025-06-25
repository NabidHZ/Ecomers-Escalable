package com.ecomers.service;

import com.ecomers.dto.RegisterRequest;
import com.ecomers.model.User;
import com.ecomers.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*public void registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.USUARIO);
        userRepository.save(user);
    }*/

    public void registerUser(RegisterRequest request) {
        System.out.println("Registrando usuario: " + request.getUsername());
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.USUARIO);
        userRepository.save(user);
        System.out.println("Usuario registrado en la base de datos.");
    }
}