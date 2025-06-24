package com.ecomers.model;

import jakarta.persistence.*;
import lombok.Data;




@Data
@Entity
@Table(name="users")




public class User {
    @Id //indica que es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que el valor se genera automáticamente por la bd
    private Long id;

    @Column(nullable = false, unique = true) //indica que no puede ser nulo y debe ser único
    private String username;

    @Column(nullable = false)
    private String password;


    @Enumerated(EnumType.STRING) // Almacena el valor como texto en la base de datos
    @Column(nullable = false)
    private Role role;

    public enum Role {
        ADMIN, USUARIO
    }
}
