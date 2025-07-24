// src/test/java/com/ecommerce/controller/AuthControllerTest.java
package com.ecommerce.controller;

import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService; // Mock necesario

    // Elimina o deja el filtro según tu proyecto
    // @MockBean
    // private JwtAuthFilter jwtAuthFilter;

    @Test
    void testRegister() throws Exception {
        String token = "fake-jwt-token";
        when(authService.register(org.mockito.ArgumentMatchers.any())).thenReturn(token);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"Password123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(token));
    }

    @Test
    void testLogin() throws Exception {
        String token = "fake-jwt-token";
        when(authService.login(org.mockito.ArgumentMatchers.any())).thenReturn(token);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"Password123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(token));
    }
}