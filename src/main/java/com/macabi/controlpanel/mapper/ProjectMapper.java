package com.macabi.controlpanel.mapper;

import com.macabi.controlpanel.dto.project.ProjectRequestDto;
import com.macabi.controlpanel.dto.project.ProjectResponseDto;
import com.macabi.controlpanel.dto.project.ProjectSummaryDto;
import com.macabi.controlpanel.dto.project.ProjectUpdateDto;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    
    public ProjectResponseDto toResponseDto(Project project) {
        if (project == null) {
            return null;
        }
        
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setDate(project.getDate());
        
        if (project.getUser() != null) {
            dto.setUserId(project.getUser().getId());
            dto.setUserName(project.getUser().getName() + " " + project.getUser().getFirstName());
        }
        
        dto.setParticipantsCount(project.getParticipants() != null ? project.getParticipants().size() : 0);
        dto.setMenusCount(project.getMenus() != null ? project.getMenus().size() : 0);
        dto.setTransfersCount(project.getTransfers() != null ? project.getTransfers().size() : 0);
        
        return dto;
    }
    
    public ProjectSummaryDto toSummaryDto(Project project) {
        if (project == null) {
            return null;
        }
        
        ProjectSummaryDto dto = new ProjectSummaryDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setDate(project.getDate());
        
        if (project.getUser() != null) {
            dto.setUserName(project.getUser().getName() + " " + project.getUser().getFirstName());
        }
        
        return dto;
    }
    
    public Project toEntity(ProjectRequestDto requestDto, User user) {
        if (requestDto == null) {
            return null;
        }
        
        Project project = new Project();
        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());
        project.setDate(requestDto.getDate());
        project.setUser(user);
        
        return project;
    }
    
    public void updateEntityFromDto(Project project, ProjectUpdateDto updateDto) {
        if (project == null || updateDto == null) {
            return;
        }
        
        project.setName(updateDto.getName());
        project.setDescription(updateDto.getDescription());
        project.setDate(updateDto.getDate());
    }
}
