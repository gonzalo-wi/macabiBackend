package com.macabi.controlpanel.dto.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantResponseDto {
    
    private Long id;
    private Long projectId;
    private Long userId;
    private String name;
    private String firstName;
    private String email;
}
