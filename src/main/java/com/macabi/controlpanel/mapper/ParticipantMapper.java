package com.macabi.controlpanel.mapper;

import com.macabi.controlpanel.dto.participant.ParticipantRequestDto;
import com.macabi.controlpanel.dto.participant.ParticipantResponseDto;
import com.macabi.controlpanel.model.Participant;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantMapper {
    
    private final ProjectMapper projectMapper;
    
    public Participant toEntity(ParticipantRequestDto dto, Project project, User user) {
        Participant participant = new Participant();
        participant.setProject(project);
        participant.setUser(user);
        return participant;
    }
    
    public Participant toEntity(Project project, User user) {
        Participant participant = new Participant();
        participant.setProject(project);
        participant.setUser(user);
        return participant;
    }
    
    public ParticipantResponseDto toResponseDto(Participant participant) {
        ParticipantResponseDto dto = new ParticipantResponseDto();
        dto.setId(participant.getId());
        dto.setProject(projectMapper.toSummaryDto(participant.getProject()));
        User user = participant.getUser();
        ParticipantResponseDto.UserSummaryDto userDto = 
            new ParticipantResponseDto.UserSummaryDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setFirstName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        dto.setUser(userDto);
        return dto;
    }
}
