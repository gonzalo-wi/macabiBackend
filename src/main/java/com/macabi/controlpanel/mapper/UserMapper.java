package com.macabi.controlpanel.mapper;

import com.macabi.controlpanel.dto.user.UserRequestDto;
import com.macabi.controlpanel.dto.user.UserResponseDto;
import com.macabi.controlpanel.dto.user.UserUpdateDto;
import com.macabi.controlpanel.model.User;
import com.macabi.controlpanel.util.PasswordUtil;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    /**
     * Convert User entity to UserResponseDto
     */
    public UserResponseDto toResponseDto(User user) {
        if (user == null) {
            return null;
        }
        
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setFirstName(user.getFirstName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        
        return dto;
    }
    
    /**
     * Convert UserRequestDto to User entity with hashed password
     */
    public User toEntity(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }
        
        User user = new User();
        user.setName(dto.getName());
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setPassword(PasswordUtil.hashPassword(dto.getPassword()));
        user.setRole(dto.getRole());
        
        return user;
    }
    
    /**
     * Update existing User entity from UserUpdateDto
     */
    public void updateEntityFromDto(UserUpdateDto dto, User user) {
        if (dto == null || user == null) {
            return;
        }
        
        if (dto.getName() != null && !dto.getName().isBlank()) {
            user.setName(dto.getName());
        }
        
        if (dto.getFirstName() != null && !dto.getFirstName().isBlank()) {
            user.setFirstName(dto.getFirstName());
        }
        
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            user.setEmail(dto.getEmail());
        }
        
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(PasswordUtil.hashPassword(dto.getPassword()));
        }
        
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
    }
}
