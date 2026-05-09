package com.macabi.controlpanel.controller;

import com.macabi.controlpanel.dto.booking.BookingMealRequestDto;
import com.macabi.controlpanel.dto.booking.BookingMealResponseDto;
import com.macabi.controlpanel.dto.booking.BookingMealUpdateDto;
import com.macabi.controlpanel.service.iservice.BookingMealService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/booking-meals")
@RequiredArgsConstructor
@Tag(name = "Booking Meals", description = "Meal booking management endpoints")
public class BookingMealController {
    
    private final BookingMealService bookingMealService;
    
    @GetMapping
    public ResponseEntity<List<BookingMealResponseDto>> getAllBookingMeals() {
        List<BookingMealResponseDto> bookingMeals = bookingMealService.getAllBookingMeals();
        return ResponseEntity.ok(bookingMeals);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BookingMealResponseDto> getBookingMealById(@PathVariable Long id) {
        BookingMealResponseDto bookingMeal = bookingMealService.getBookingMealById(id);
        return ResponseEntity.ok(bookingMeal);
    }
    
    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<BookingMealResponseDto>> getBookingMealsByParticipantId(@PathVariable Long participantId) {
        List<BookingMealResponseDto> bookingMeals = bookingMealService.getBookingMealsByParticipantId(participantId);
        return ResponseEntity.ok(bookingMeals);
    }
    
    @GetMapping("/participant/{participantId}/active")
    public ResponseEntity<List<BookingMealResponseDto>> getActiveBookingMealsByParticipantId(@PathVariable Long participantId) {
        List<BookingMealResponseDto> bookingMeals = bookingMealService.getActiveBookingMealsByParticipantId(participantId);
        return ResponseEntity.ok(bookingMeals);
    }
    
    @GetMapping("/meal/{mealId}")
    public ResponseEntity<List<BookingMealResponseDto>> getBookingMealsByMealId(@PathVariable Long mealId) {
        List<BookingMealResponseDto> bookingMeals = bookingMealService.getBookingMealsByMealId(mealId);
        return ResponseEntity.ok(bookingMeals);
    }
    
    @GetMapping("/project/{projectId}/active")
    public ResponseEntity<List<BookingMealResponseDto>> getActiveBookingMealsByProjectId(@PathVariable Long projectId) {
        List<BookingMealResponseDto> bookingMeals = bookingMealService.getActiveBookingMealsByProjectId(projectId);
        return ResponseEntity.ok(bookingMeals);
    }
    
    @PostMapping
    public ResponseEntity<BookingMealResponseDto> createBookingMeal(@Valid @RequestBody BookingMealRequestDto bookingMealDto) {
        BookingMealResponseDto createdBookingMeal = bookingMealService.createBookingMeal(bookingMealDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookingMeal);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BookingMealResponseDto> updateBookingMeal(
            @PathVariable Long id,
            @Valid @RequestBody BookingMealUpdateDto bookingMealDto) {
        BookingMealResponseDto updatedBookingMeal = bookingMealService.updateBookingMeal(id, bookingMealDto);
        return ResponseEntity.ok(updatedBookingMeal);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingMeal(@PathVariable Long id) {
        bookingMealService.deleteBookingMeal(id);
        return ResponseEntity.noContent().build();
    }
}
