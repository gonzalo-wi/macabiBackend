package com.macabi.controlpanel.service.iservice;

import com.macabi.controlpanel.dto.meal.MealRequestDto;
import com.macabi.controlpanel.dto.meal.MealResponseDto;
import com.macabi.controlpanel.dto.meal.MealUpdateDto;
import java.util.List;

public interface MealService {
    
    List<MealResponseDto> getAllMeals();
    MealResponseDto getMealById(Long id);
    MealResponseDto createMeal(MealRequestDto mealDto);
    MealResponseDto updateMeal(Long id, MealUpdateDto mealDto);
    void deleteMeal(Long id);
}
