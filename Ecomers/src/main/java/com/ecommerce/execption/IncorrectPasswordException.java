package com.ecommerce.execption;

/**
 * Excepción lanzada cuando la contraseña proporcionada es incorrecta.
 */
public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}