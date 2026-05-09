package com.macabi.controlpanel.repository;

import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    List<Project> findByUser(User user);
    
    List<Project> findByUserId(Long userId);
    
    List<Project> findByDate(LocalDate date);
    
    List<Project> findByNameContainingIgnoreCase(String name);
}
