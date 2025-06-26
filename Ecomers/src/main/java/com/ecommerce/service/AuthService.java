package com.ecommerce.service;

import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.security.JwtUtil;
import com.ecommerce.execption.*;
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

    public String register(RegisterRequest request) { // Este método maneja el registro de un nuevo usuario.
        // Validar formato del email
        if (!request.email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidEmailException("El formato del correo electrónico es inválido.");
        }

        // Verificar si el usuario ya existe
        if (userRepository.findByEmail(request.email) != null) {
            throw new UserAlreadyExistsException("El usuario con este correo ya existe.");
        }

        // Validar longitud de la contraseña
        if (request.password.length() < 8) {
            throw new WeakPasswordException("La contraseña debe tener al menos 8 caracteres.");
        }

        //En caso de que no se cumplan las validaciones, se crea un nuevo usuario y se guarda en la base de datos.
        User user = new User();
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setProvider("LOCAL");
        userRepository.save(user);
        return jwtUtil.generateToken(user.getEmail());
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email);

        // Verificar si el usuario existe
        if (user == null) {
            throw new UserNotFoundException("No se encontró un usuario con este correo.");
        }

        // Validar la contraseña
        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new IncorrectPasswordException("La contraseña es incorrecta.");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}