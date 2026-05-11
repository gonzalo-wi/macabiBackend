package com.macabi.controlpanel.service.iservice;

import com.macabi.controlpanel.dto.auth.AuthResponseDto;
import com.macabi.controlpanel.dto.auth.LoginRequestDto;

public interface AuthService {

    AuthResponseDto login(LoginRequestDto loginRequest);
}
