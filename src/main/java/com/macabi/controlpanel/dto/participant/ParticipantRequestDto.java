package com.macabi.controlpanel.dto.participant;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantRequestDto {
    
    @NotNull(message = "User ID is required")
    private Long userId;
}
