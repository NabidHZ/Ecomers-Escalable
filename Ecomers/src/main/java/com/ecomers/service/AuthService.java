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

    public void registerUser(RegisterRequest request) { // Este metodo maneja el registro de usuarios, es invicado por AuthController
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.USUARIO);
        userRepository.save(user);
    }//Este usuario es enviado a Repository para ser guarada en la Base de datos


}