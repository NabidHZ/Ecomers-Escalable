package com.ecommerce.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.security.Key;

@Component
public class JwtUtil { //Esta clase genera y valida los tokens JWT.


    @Value("${jwt.secret}") // Clave secreta para firmar los tokens, debe ser configurada en application.properties
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(String email) { // Este método genera un token JWT para un usuario dado su email.
        return Jwts.builder()
                .setSubject(email) // El sujeto del token es el email del usuario
                .setIssuedAt(new Date()) // Fecha de emisión del token
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))  // Fecha de expiración del token, se calcula sumando el tiempo de expiración configurado
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Firma el token con la clave secreta y el algoritmo HS256
                .compact();
    }

    public String getEmailFromToken(String token) {  // Este método extrae el email del sujeto del token JWT.
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Configura la clave de firma para validar el token
                .build()  // Construye el parser de JWT
                .parseClaimsJws(token)  // Analiza el token y obtiene los claims
                .getBody() // Obtiene el cuerpo de los claims del token
                .getSubject(); // Retorna el sujeto del token, que es el email del usuario
    }

    public boolean validateToken(String token) { // Este método valida el token JWT.Esto incluye comprobar que el token no esté alterado y que no haya caducado.
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) //
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}