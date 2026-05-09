package com.macabi.controlpanel.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.macabi.controlpanel.dto.participant.ParticipantResponseDto;
import com.macabi.controlpanel.dto.project.ProjectRequestDto;
import com.macabi.controlpanel.dto.project.ProjectResponseDto;
import com.macabi.controlpanel.dto.project.ProjectSummaryDto;
import com.macabi.controlpanel.dto.project.ProjectUpdateDto;
import com.macabi.controlpanel.exception.DuplicateParticipantException;
import com.macabi.controlpanel.exception.ResourceNotFoundException;
import com.macabi.controlpanel.mapper.ParticipantMapper;
import com.macabi.controlpanel.mapper.ProjectMapper;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.User;
import com.macabi.controlpanel.repository.ParticipantRepository;
import com.macabi.controlpanel.repository.ProjectRepository;
import com.macabi.controlpanel.repository.UserRepository;
import com.macabi.controlpanel.service.iservice.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private static final String USER_RESOURCE             = "User";
    private static final String PROJECT_RESOURCE          = "Project";
    private static final String ID_FIELD                  = "id";
    private static final String PARTICIPANT_NOT_FOUND_MSG = "Participant not found for project %d and user %d";
    @Autowired
    private ProjectRepository     projectRepository;
    @Autowired
    private UserRepository        userRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ProjectMapper         projectMapper;
    @Autowired
    private ParticipantMapper     participantMapper;



    @Override
    @Transactional(readOnly = true)
    public List<ProjectSummaryDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectSummaryDto> getProjectsByUserId(Long userId) {
        validateUserExists(userId);
        return projectRepository.findByUserId(userId).stream()
                .map(projectMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectResponseDto getProjectById(Long id) {
        var project = findProjectById(id);
        return projectMapper.toResponseDto(project);
    }

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto requestDto) {
        var user         = findUserById(requestDto.getUserId());
        var project      = projectMapper.toEntity(requestDto, user);
        var savedProject = projectRepository.save(project);
        return projectMapper.toResponseDto(savedProject);
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectUpdateDto updateDto) {
        var existingProject = findProjectById(id);
        projectMapper.updateEntityFromDto(existingProject, updateDto);
        var updatedProject  = projectRepository.save(existingProject);
        return projectMapper.toResponseDto(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        validateProjectExists(id);
        projectRepository.deleteById(id);
    }

    // Logica de participantes

    @Override
    public ParticipantResponseDto assignParticipant(Long projectId, Long userId) {
        var project = findProjectById(projectId);
        var user    = findUserById(userId);
        validateParticipantNotExists(projectId, userId);
        var participant      = participantMapper.toEntity(project, user);
        var savedParticipant = participantRepository.save(participant);
        return participantMapper.toResponseDto(savedParticipant);
    }


    @Override
    public void removeParticipant(Long projectId, Long userId) {
        validateProjectExists(projectId);
        validateUserExists(userId);
        var participant = participantRepository.findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    String.format(PARTICIPANT_NOT_FOUND_MSG, projectId, userId)));
        participantRepository.delete(participant);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ParticipantResponseDto> getProjectParticipants(Long projectId) {
        validateProjectExists(projectId);
        return participantRepository.findByProjectId(projectId).stream()
                .map(participantMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    //Validaciones
    
    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(USER_RESOURCE, ID_FIELD, userId);
        }
    }

    private void validateProjectExists(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException(PROJECT_RESOURCE, projectId);
        }
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_RESOURCE, userId));
    }

    private Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(PROJECT_RESOURCE, projectId));
    }

    private void validateParticipantNotExists(Long projectId, Long userId) {
        if (participantRepository.existsByProjectIdAndUserId(projectId, userId)) {
            throw new DuplicateParticipantException(projectId, userId);
        }
    }
}
