package com.ecommerce.model;

import jakarta.persistence.*;

@Entity // Anotación para indicar que esta clase es una entidad JPA
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera un ID único para cada usuario
    private Long id;

    @Column(unique = true)
    private String email;

    private String password; // Solo para login propio

    private String provider; // "LOCAL" o "GOOGLE"

    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}