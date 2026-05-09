package com.macabi.controlpanel.mapper;

import org.springframework.stereotype.Component;

import com.macabi.controlpanel.dto.participant.ParticipantResponseDto;
import com.macabi.controlpanel.model.Participant;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.User;

@Component
public class ParticipantMapper {
    
    public ParticipantResponseDto toResponseDto(Participant participant) {
        if (participant == null) {
            return null;
        }
        
        ParticipantResponseDto dto = new ParticipantResponseDto();
        dto.setId(participant.getId());
        dto.setProjectId(participant.getProject().getId());
        dto.setUserId(participant.getUser().getId());
        dto.setName(participant.getName());
        dto.setFirstName(participant.getFirstName());
        dto.setEmail(participant.getEmail());
        
        return dto;
    }
    
    public Participant toEntity(Project project, User user) {
        if (project == null || user == null) {
            return null;
        }
        
        Participant participant = new Participant();
        participant.setProject(project);
        participant.setUser(user);
        participant.setName(user.getName());
        participant.setFirstName(user.getFirstName());
        participant.setEmail(user.getEmail());
        
        return participant;
    }
}
