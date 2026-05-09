package com.macabi.controlpanel.service.iservice;

import com.macabi.controlpanel.dto.participant.ParticipantRequestDto;
import com.macabi.controlpanel.dto.participant.ParticipantResponseDto;
import java.util.List;

public interface ParticipantService {
    
    List<ParticipantResponseDto> getAllParticipants();
    ParticipantResponseDto getParticipantById(Long id);
    List<ParticipantResponseDto> getParticipantsByProjectId(Long projectId);
    List<ParticipantResponseDto> getParticipantsByUserId(Long userId);
    ParticipantResponseDto createParticipant(ParticipantRequestDto participantDto);
    void deleteParticipant(Long id);
}
