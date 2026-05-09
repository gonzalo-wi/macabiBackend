package com.macabi.controlpanel.service.iservice;

import com.macabi.controlpanel.dto.participant.ParticipantResponseDto;
import com.macabi.controlpanel.dto.project.ProjectRequestDto;
import com.macabi.controlpanel.dto.project.ProjectResponseDto;
import com.macabi.controlpanel.dto.project.ProjectSummaryDto;
import com.macabi.controlpanel.dto.project.ProjectUpdateDto;

import java.util.List;

public interface ProjectService {
    
    // Crud
    List<ProjectSummaryDto> getAllProjects();
    List<ProjectSummaryDto> getProjectsByUserId(Long userId);
    ProjectResponseDto getProjectById(Long id);
    ProjectResponseDto createProject(ProjectRequestDto requestDto);
    ProjectResponseDto updateProject(Long id, ProjectUpdateDto updateDto);
    void deleteProject(Long id);
    
    // metodos participantes
    ParticipantResponseDto assignParticipant(Long projectId, Long userId);
    void removeParticipant(Long projectId, Long userId);
    List<ParticipantResponseDto> getProjectParticipants(Long projectId);
}
