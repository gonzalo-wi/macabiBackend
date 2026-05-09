package com.macabi.controlpanel.service.iservice;

import com.macabi.controlpanel.dto.user.UserRequestDto;
import com.macabi.controlpanel.dto.user.UserResponseDto;
import com.macabi.controlpanel.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {
    
    /**
     * Get all users
     */
    List<UserResponseDto> getAllUsers();
    
    /**
     * Get user by ID
     */
    UserResponseDto getUserById(Long id);
    
    /**
     * Get user by email
     */
    UserResponseDto getUserByEmail(String email);
    
    /**
     * Create a new user
     */
    UserResponseDto createUser(UserRequestDto userDto);
    
    /**
     * Update an existing user
     */
    UserResponseDto updateUser(Long id, UserUpdateDto userDto);
    
    /**
     * Delete a user
     */
    void deleteUser(Long id);
}
