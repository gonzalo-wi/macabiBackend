package com.macabi.controlpanel.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final String MESSAGE_INVALID_PASSWORD = "Password cannot be null or empty";
    
    private PasswordUtil() {
    }
    
    public static String hashPassword(String plainPassword) {
        validatePassword(plainPassword);
        return encoder.encode(plainPassword);
    }
    
    
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (!isValidInput(plainPassword, hashedPassword)) {
            return false;
        }
        return encoder.matches(plainPassword, hashedPassword);
    }

   
    private static void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_PASSWORD);
        }
    }

    private static boolean isValidInput(String plainPassword, String hashedPassword) {
        return plainPassword != null && hashedPassword != null;
    }
}
