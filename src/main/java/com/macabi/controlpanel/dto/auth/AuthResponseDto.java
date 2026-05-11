package com.macabi.controlpanel.dto.auth;

import com.macabi.controlpanel.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {

    private String token;
    private Long   id;
    private String name;
    private String firstName;
    private String email;
    private Role   role;
}
