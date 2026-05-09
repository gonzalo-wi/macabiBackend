package com.macabi.controlpanel.exception;

public class DuplicateEmailException extends RuntimeException {
    
    public DuplicateEmailException(String message) {
        super(message);
    }
    
    public DuplicateEmailException(String email, String message) {
        super(String.format("Email '%s' is already in use. %s", email, message));
    }
}
