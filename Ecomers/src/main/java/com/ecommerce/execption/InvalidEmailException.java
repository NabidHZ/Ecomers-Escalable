package com.ecommerce.execption;

/**
 * Excepción lanzada cuando el email proporcionado no tiene un formato válido.
 */
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}