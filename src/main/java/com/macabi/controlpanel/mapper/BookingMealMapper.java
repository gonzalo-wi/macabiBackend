package com.macabi.controlpanel.mapper;

import com.macabi.controlpanel.dto.booking.BookingMealRequestDto;
import com.macabi.controlpanel.dto.booking.BookingMealResponseDto;
import com.macabi.controlpanel.dto.booking.BookingMealUpdateDto;
import com.macabi.controlpanel.model.BookingMeal;
import com.macabi.controlpanel.model.Meal;
import com.macabi.controlpanel.model.Participant;
import com.macabi.controlpanel.model.enums.TypeBooking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMealMapper {
    
    private final MealMapper mealMapper;
    
    public BookingMeal toEntity(BookingMealRequestDto dto, Participant participant, Meal meal) {
        BookingMeal bookingMeal = new BookingMeal();
        bookingMeal.setParticipant(participant);
        bookingMeal.setMeal(meal);
        bookingMeal.setTypeMenu(dto.getTypeMenu());
        bookingMeal.setTypeBooking(TypeBooking.MEAL);
        bookingMeal.setActive(dto.getActive());
        return bookingMeal;
    }
    
    public BookingMealResponseDto toResponseDto(BookingMeal bookingMeal) {
        BookingMealResponseDto dto = new BookingMealResponseDto();
        dto.setId(bookingMeal.getId());
        dto.setTypeBooking(bookingMeal.getTypeBooking());
        dto.setActive(bookingMeal.isActive());
        dto.setTypeMenu(bookingMeal.getTypeMenu());
        Participant participant = bookingMeal.getParticipant();
        BookingMealResponseDto.ParticipantSummaryDto participantDto = 
            new BookingMealResponseDto.ParticipantSummaryDto();
        participantDto.setId(participant.getId());
        participantDto.setName(participant.getUser().getName());
        participantDto.setFirstName(participant.getUser().getFirstName());
        participantDto.setEmail(participant.getUser().getEmail());
        participantDto.setProjectId(participant.getProject().getId());
        participantDto.setProjectName(participant.getProject().getName());
        dto.setParticipant(participantDto);
        dto.setMeal(mealMapper.toResponseDto(bookingMeal.getMeal()));
        return dto;
    }
    
    public void updateEntityFromDto(BookingMealUpdateDto dto, BookingMeal bookingMeal, Meal meal) {
        if (dto.getMealId() != null && meal != null) {
            bookingMeal.setMeal(meal);
        }
        if (dto.getTypeMenu() != null) {
            bookingMeal.setTypeMenu(dto.getTypeMenu());
        }
        if (dto.getActive() != null) {
            bookingMeal.setActive(dto.getActive());
        }
    }
}
