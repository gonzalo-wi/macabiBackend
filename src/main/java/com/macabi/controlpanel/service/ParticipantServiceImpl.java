package com.macabi.controlpanel.service;

import com.macabi.controlpanel.dto.participant.ParticipantRequestDto;
import com.macabi.controlpanel.dto.participant.ParticipantResponseDto;
import com.macabi.controlpanel.exception.DuplicateParticipantException;
import com.macabi.controlpanel.exception.ResourceNotFoundException;
import com.macabi.controlpanel.mapper.ParticipantMapper;
import com.macabi.controlpanel.model.Participant;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.User;
import com.macabi.controlpanel.repository.ParticipantRepository;
import com.macabi.controlpanel.repository.ProjectRepository;
import com.macabi.controlpanel.repository.UserRepository;
import com.macabi.controlpanel.service.iservice.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    
    private static final String PARTICIPANT_RESOURCE      = "Participante";
    private static final String PROJECT_RESOURCE          = "Proyecto";
    private static final String USER_RESOURCE             = "Usuario";
    private static final String ID_FIELD                  = "id";
    private static final String DUPLICATE_PARTICIPANT_MSG = "El participante con ID de usuario %d ya existe en el proyecto ID %d";
    private final ParticipantRepository participantRepository;
    private final ProjectRepository     projectRepository;
    private final UserRepository        userRepository;
    private final ParticipantMapper participantMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ParticipantResponseDto> getAllParticipants() {
        return participantRepository.findAll().stream()
                .map(participantMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ParticipantResponseDto getParticipantById(Long id) {
        var participant = findParticipantById(id);
        return participantMapper.toResponseDto(participant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipantResponseDto> getParticipantsByProjectId(Long projectId) {
        validateProjectExists(projectId);
        return participantRepository.findByProjectId(projectId).stream()
                .map(participantMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipantResponseDto> getParticipantsByUserId(Long userId) {
        validateUserExists(userId);
        return participantRepository.findByUserId(userId).stream()
                .map(participantMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipantResponseDto createParticipant(ParticipantRequestDto participantDto) {
        var project = findProjectById(participantDto.getProjectId());
        var user    = findUserById(participantDto.getUserId());
        validateUniqueParticipant(participantDto.getProjectId(), participantDto.getUserId());
        var participant      = participantMapper.toEntity(participantDto, project, user);
        var savedParticipant = participantRepository.save(participant);
        return participantMapper.toResponseDto(savedParticipant);
    }

    @Override
    public void deleteParticipant(Long id) {
        var participant = findParticipantById(id);
        participantRepository.delete(participant);
    }
    
    // metodos privados
    
    private Participant findParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        PARTICIPANT_RESOURCE, ID_FIELD, id));
    }
    
    private Project findProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        PROJECT_RESOURCE, ID_FIELD, id));
    }
    
    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        USER_RESOURCE, ID_FIELD, id));
    }
    
    private void validateProjectExists(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException(PROJECT_RESOURCE, ID_FIELD, projectId);
        }
    }
    
    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(USER_RESOURCE, ID_FIELD, userId);
        }
    }
    
    private void validateUniqueParticipant(Long projectId, Long userId) {
        if (participantRepository.existsByProjectIdAndUserId(projectId, userId)) {
            throw new DuplicateParticipantException(
                String.format(DUPLICATE_PARTICIPANT_MSG, userId, projectId));
        }
    }
}
