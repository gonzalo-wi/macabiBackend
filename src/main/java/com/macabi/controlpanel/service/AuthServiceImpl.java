package com.macabi.controlpanel.service;

import com.macabi.controlpanel.dto.auth.AuthResponseDto;
import com.macabi.controlpanel.dto.auth.LoginRequestDto;
import com.macabi.controlpanel.exception.ResourceNotFoundException;
import com.macabi.controlpanel.model.User;
import com.macabi.controlpanel.repository.UserRepository;
import com.macabi.controlpanel.service.iservice.AuthService;
import com.macabi.controlpanel.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String USER_RESOURCE = "User";
    private static final String EMAIL_FIELD   = "email";
    private final AuthenticationManager authenticationManager;
    private final UserRepository        userRepository;
    private final JwtService            jwtService;

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());
        User user    = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(USER_RESOURCE, EMAIL_FIELD, loginRequest.getEmail()));
        return new AuthResponseDto(token, user.getId(), user.getName(), user.getFirstName(), user.getEmail(), user.getRole());
    }
}
