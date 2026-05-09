package com.macabi.controlpanel.repository;

import com.macabi.controlpanel.model.Menu;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.enums.TypeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    
    List<Menu> findByProject(Project project);
    
    List<Menu> findByProjectId(Long projectId);
    
    List<Menu> findByProjectIdAndActive(Long projectId, boolean active);
    
    Optional<Menu> findByProjectIdAndDateAndTypeMenu(Long projectId, LocalDate date, TypeMenu typeMenu);
    
    List<Menu> findByTypeMenu(TypeMenu typeMenu);
}
