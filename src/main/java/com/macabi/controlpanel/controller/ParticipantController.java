package com.macabi.controlpanel.controller;
import com.macabi.controlpanel.dto.participant.ParticipantRequestDto;
import com.macabi.controlpanel.dto.participant.ParticipantResponseDto;
import com.macabi.controlpanel.service.iservice.ParticipantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
@Tag(name = "Participants", description = "Participant management endpoints")
public class ParticipantController {
    
    private final ParticipantService participantService;
    
    @GetMapping
    public ResponseEntity<List<ParticipantResponseDto>> getAllParticipants() {
        List<ParticipantResponseDto> participants = participantService.getAllParticipants();
        return ResponseEntity.ok(participants);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ParticipantResponseDto> getParticipantById(@PathVariable Long id) {
        ParticipantResponseDto participant = participantService.getParticipantById(id);
        return ResponseEntity.ok(participant);
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ParticipantResponseDto>> getParticipantsByProjectId(@PathVariable Long projectId) {
        List<ParticipantResponseDto> participants = participantService.getParticipantsByProjectId(projectId);
        return ResponseEntity.ok(participants);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ParticipantResponseDto>> getParticipantsByUserId(@PathVariable Long userId) {
        List<ParticipantResponseDto> participants = participantService.getParticipantsByUserId(userId);
        return ResponseEntity.ok(participants);
    }
    
    @PostMapping
    public ResponseEntity<ParticipantResponseDto> createParticipant(@Valid @RequestBody ParticipantRequestDto participantDto) {
        ParticipantResponseDto createdParticipant = participantService.createParticipant(participantDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParticipant);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}
