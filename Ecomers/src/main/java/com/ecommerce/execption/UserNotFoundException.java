package com.ecommerce.execption;

/**
 * Excepción lanzada cuando no se encuentra un usuario con el email proporcionado.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}