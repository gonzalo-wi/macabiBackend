package com.macabi.controlpanel.service.iservice;

import com.macabi.controlpanel.dto.booking.BookingMealRequestDto;
import com.macabi.controlpanel.dto.booking.BookingMealResponseDto;
import com.macabi.controlpanel.dto.booking.BookingMealUpdateDto;
import java.util.List;

public interface BookingMealService {
    
    List<BookingMealResponseDto> getAllBookingMeals();
    BookingMealResponseDto getBookingMealById(Long id);
    List<BookingMealResponseDto> getBookingMealsByParticipantId(Long participantId);
    List<BookingMealResponseDto> getActiveBookingMealsByParticipantId(Long participantId);
    List<BookingMealResponseDto> getBookingMealsByMealId(Long mealId);
    List<BookingMealResponseDto> getActiveBookingMealsByProjectId(Long projectId);
    BookingMealResponseDto createBookingMeal(BookingMealRequestDto bookingMealDto);
    BookingMealResponseDto updateBookingMeal(Long id, BookingMealUpdateDto bookingMealDto);
    void deleteBookingMeal(Long id);
}
