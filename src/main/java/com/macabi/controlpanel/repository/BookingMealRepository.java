package com.macabi.controlpanel.repository;

import com.macabi.controlpanel.model.BookingMeal;
import com.macabi.controlpanel.model.Participant;
import com.macabi.controlpanel.model.enums.TypeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingMealRepository extends JpaRepository<BookingMeal, Long> {
    
    List<BookingMeal> findByParticipant(Participant participant);
    List<BookingMeal> findByParticipantId(Long participantId);
    List<BookingMeal> findByParticipantIdAndActive(Long participantId, boolean active);
    List<BookingMeal> findByMealId(Long mealId);
    List<BookingMeal> findByTypeMenu(TypeMenu typeMenu);
    
    @Query("SELECT bm FROM BookingMeal bm WHERE bm.participant.project.id = :projectId AND bm.active = true")
    List<BookingMeal> findByProjectIdAndActive(@Param("projectId") Long projectId);
}
