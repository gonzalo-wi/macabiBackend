package com.macabi.controlpanel.repository;

import com.macabi.controlpanel.model.Participant;
import com.macabi.controlpanel.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    
    List<Participant> findByProject(Project project);
    
    List<Participant> findByProjectId(Long projectId);
    
    Optional<Participant> findByProjectIdAndEmail(Long projectId, String email);
    
    Optional<Participant> findByProjectIdAndUserId(Long projectId, Long userId);
    
    boolean existsByProjectIdAndUserId(Long projectId, Long userId);
    
    List<Participant> findByUserId(Long userId);
}
