package com.macabi.controlpanel.service;

import com.macabi.controlpanel.dto.user.UserRequestDto;
import com.macabi.controlpanel.dto.user.UserResponseDto;
import com.macabi.controlpanel.dto.user.UserUpdateDto;
import com.macabi.controlpanel.exception.DuplicateEmailException;
import com.macabi.controlpanel.exception.ResourceNotFoundException;
import com.macabi.controlpanel.mapper.UserMapper;
import com.macabi.controlpanel.model.User;
import com.macabi.controlpanel.model.enums.Role;
import com.macabi.controlpanel.repository.UserRepository;
import com.macabi.controlpanel.service.iservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private static final String  USER_RESOURCE    = "User";
    private static final String  ID_FIELD         = "id";
    private static final String  EMAIL_FIELD      = "email";
    private static final String  EMAIL_EXISTS_MSG = "Please choose a different email address.";
    private final UserRepository userRepository;
    private final UserMapper     userMapper;
    

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        var user = findUserById(id);
        return userMapper.toResponseDto(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        USER_RESOURCE, EMAIL_FIELD, email));
        return userMapper.toResponseDto(user);
    }
    
    @Override
    public UserResponseDto createUser(UserRequestDto userDto) {
        validateEmailNotExists(userDto.getEmail());
        var user      = userMapper.toEntity(userDto);
        var savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }
    
    @Override
    public UserResponseDto updateUser(Long id, UserUpdateDto userDto) {
        var user = findUserById(id);
        if (userDto.getEmail() != null && !userDto.getEmail().equals(user.getEmail())) {
            validateEmailNotExists(userDto.getEmail());
        }
        userMapper.updateEntityFromDto(userDto, user);
        var updatedUser = userRepository.save(user);
        return userMapper.toResponseDto(updatedUser);
    }
    

    @Override
    public void deleteUser(Long id) {
        var user = findUserById(id);
        userRepository.delete(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsersByRole(Role role) {
        return userRepository.findByRole(role).stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // Private validation methods
    
    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        USER_RESOURCE, ID_FIELD, id));
    }
    
    private void validateEmailNotExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(email, EMAIL_EXISTS_MSG);
        }
    }
}
