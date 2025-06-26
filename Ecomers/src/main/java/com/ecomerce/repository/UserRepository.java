package com.ecomerce.repository;

import com.ecomerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// Este repositorio extiende JpaRepository, lo que proporciona métodos CRUD básicos para la entidad User.
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
