package com.macabi.controlpanel.repository;

import com.macabi.controlpanel.model.Transfer;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.enums.TypeTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
 
    List<Transfer> findByProject(Project project);
    List<Transfer> findByProjectId(Long projectId);
    List<Transfer> findByProjectIdAndActive(Long projectId, boolean active);
    List<Transfer> findByTypeTransfer(TypeTransfer typeTransfer);
    List<Transfer> findByProjectIdAndTypeTransfer(Long projectId, TypeTransfer typeTransfer);
    List<Transfer> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
