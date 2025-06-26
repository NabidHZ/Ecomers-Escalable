package com.ecommerce.repository;

import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { //El repositorio interactúa con la base de datos para realizar operaciones CRUD.
    User findByEmail(String email);
}