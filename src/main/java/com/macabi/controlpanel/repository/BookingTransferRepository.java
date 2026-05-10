package com.macabi.controlpanel.repository;

import com.macabi.controlpanel.model.BookingTransfer;
import com.macabi.controlpanel.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingTransferRepository extends JpaRepository<BookingTransfer, Long> {
    
    List<BookingTransfer> findByParticipant(Participant participant);
    List<BookingTransfer> findByParticipantId(Long participantId);
    List<BookingTransfer> findByParticipantIdAndActive(Long participantId, boolean active);
    List<BookingTransfer> findByTransferId(Long transferId);
    @Query("SELECT bt FROM BookingTransfer bt WHERE bt.participant.project.id = :projectId AND bt.active = true")
    List<BookingTransfer> findByProjectIdAndActive(@Param("projectId") Long projectId);
    
    @Query("SELECT COUNT(bt) FROM BookingTransfer bt WHERE bt.transfer.id = :transferId AND bt.active = true")
    Long countActiveByTransferId(@Param("transferId") Long transferId);
}
