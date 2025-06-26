package com.ecommerce.security;

import com.ecommerce.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;


    /**
     * Este filtro se encarga de interceptar las solicitudes HTTP para verificar la validez del token JWT.
     * Si el token es válido, se establece la autenticación en el contexto de seguridad.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) // Método que se ejecuta para filtrar las solicitudes HTTP
            throws ServletException, IOException {


        String authHeader = request.getHeader("Authorization"); // Obtiene el encabezado de autorización de la solicitud HTTP

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // Verifica si el encabezado de autorización está presente y comienza con "Bearer "
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7); // Extrae el token JWT del encabezado de autorización, eliminando el prefijo "Bearer "

        try { // Intenta validar el token JWT y extraer el email del usuario
            String email = jwtUtil.getEmailFromToken(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) { // Verifica si el email no es nulo y si no hay una autenticación previa en el contexto de seguridad
                var user = userRepository.findByEmail(email);

                if (user != null && jwtUtil.validateToken(token)) { // Busca al usuario en la base de datos por su email y valida el token JWT
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user, null, null);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Crea un objeto de autenticación con los detalles de la solicitud
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtException e) { // Captura cualquier excepción relacionada con el token JWT
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
            return;
        }

        filterChain.doFilter(request, response);
    }
}