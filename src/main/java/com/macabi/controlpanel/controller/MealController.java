package com.macabi.controlpanel.controller;

import com.macabi.controlpanel.dto.meal.MealRequestDto;
import com.macabi.controlpanel.dto.meal.MealResponseDto;
import com.macabi.controlpanel.dto.meal.MealUpdateDto;
import com.macabi.controlpanel.service.iservice.MealService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
@Tag(name = "Meals", description = "Meal management endpoints")
public class MealController {
    
    private final MealService mealService;
    
    @GetMapping
    public ResponseEntity<List<MealResponseDto>> getAllMeals() {
        List<MealResponseDto> meals = mealService.getAllMeals();
        return ResponseEntity.ok(meals);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MealResponseDto> getMealById(@PathVariable Long id) {
        MealResponseDto meal = mealService.getMealById(id);
        return ResponseEntity.ok(meal);
    }
    
    @PostMapping
    public ResponseEntity<MealResponseDto> createMeal(@Valid @RequestBody MealRequestDto mealDto) {
        MealResponseDto createdMeal = mealService.createMeal(mealDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeal);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MealResponseDto> updateMeal(
            @PathVariable Long id,
            @Valid @RequestBody MealUpdateDto mealDto) {
        MealResponseDto updatedMeal = mealService.updateMeal(id, mealDto);
        return ResponseEntity.ok(updatedMeal);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }
}
