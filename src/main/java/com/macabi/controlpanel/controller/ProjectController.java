package com.macabi.controlpanel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.macabi.controlpanel.dto.participant.ParticipantResponseDto;
import com.macabi.controlpanel.dto.project.ProjectRequestDto;
import com.macabi.controlpanel.dto.project.ProjectResponseDto;
import com.macabi.controlpanel.dto.project.ProjectSummaryDto;
import com.macabi.controlpanel.dto.project.ProjectUpdateDto;
import com.macabi.controlpanel.service.iservice.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "API para gestión de proyectos y participantes")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    // Crud endpoints

    @GetMapping
    public ResponseEntity<List<ProjectSummaryDto>> getAllProjects() {
        List<ProjectSummaryDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectSummaryDto>> getProjectsByUserId(@PathVariable Long userId) {
        List<ProjectSummaryDto> projects = projectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        ProjectResponseDto project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto requestDto) {
        ProjectResponseDto createdProject = projectService.createProject(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectUpdateDto updateDto) {
        ProjectResponseDto updatedProject = projectService.updateProject(id, updateDto);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    // Gestion de participantes endpoints

    @PostMapping("/{projectId}/participants")
    public ResponseEntity<ParticipantResponseDto> assignParticipant(
            @PathVariable Long projectId,
            @RequestParam Long userId) {
        ParticipantResponseDto participant = projectService.assignParticipant(projectId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(participant);
    }

    @DeleteMapping("/{projectId}/participants/{userId}")
    public ResponseEntity<Void> removeParticipant(
            @PathVariable Long projectId,
            @PathVariable Long userId) {
        projectService.removeParticipant(projectId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectId}/participants")
    public ResponseEntity<List<ParticipantResponseDto>> getProjectParticipants(@PathVariable Long projectId) {
        List<ParticipantResponseDto> participants = projectService.getProjectParticipants(projectId);
        return ResponseEntity.ok(participants);
    }
}
