package com.macabi.controlpanel.dto.participant;

import com.macabi.controlpanel.dto.project.ProjectSummaryDto;
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
    private ProjectSummaryDto project;
    private UserSummaryDto user;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserSummaryDto {
        private Long id;
        private String name;
        private String firstName;
        private String email;
    }
}
