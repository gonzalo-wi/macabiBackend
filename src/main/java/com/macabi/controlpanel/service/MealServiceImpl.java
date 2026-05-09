package com.macabi.controlpanel.service;

import com.macabi.controlpanel.dto.meal.MealRequestDto;
import com.macabi.controlpanel.dto.meal.MealResponseDto;
import com.macabi.controlpanel.dto.meal.MealUpdateDto;
import com.macabi.controlpanel.exception.ResourceNotFoundException;
import com.macabi.controlpanel.mapper.MealMapper;
import com.macabi.controlpanel.model.Meal;
import com.macabi.controlpanel.repository.MealRepository;
import com.macabi.controlpanel.service.iservice.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    
    private static final String  MEAL_RESOURCE = "Meal";
    private static final String  ID_FIELD      = "id";
    private final MealRepository mealRepository;
    private final MealMapper     mealMapper;


    @Override
    @Transactional(readOnly = true)
    public List<MealResponseDto> getAllMeals() {
        return mealRepository.findAll().stream()
                .map(mealMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MealResponseDto getMealById(Long id) {
        var meal = findMealById(id);
        return mealMapper.toResponseDto(meal);
    }

    @Override
    public MealResponseDto createMeal(MealRequestDto mealDto) {
        var meal      = mealMapper.toEntity(mealDto);
        var savedMeal = mealRepository.save(meal);
        return mealMapper.toResponseDto(savedMeal);
    }

    @Override
    public MealResponseDto updateMeal(Long id, MealUpdateDto mealDto) {
        var meal        = findMealById(id);
        mealMapper.updateEntityFromDto(mealDto, meal);
        var updatedMeal = mealRepository.save(meal);
        return mealMapper.toResponseDto(updatedMeal);
    }

    @Override
    public void deleteMeal(Long id) {
        var meal = findMealById(id);
        mealRepository.delete(meal);
    }
    
    // Metodos privados
    
    private Meal findMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MEAL_RESOURCE, ID_FIELD, id));
    }
}
