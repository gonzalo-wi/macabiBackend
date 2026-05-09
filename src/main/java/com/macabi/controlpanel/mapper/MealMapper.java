package com.macabi.controlpanel.mapper;

import com.macabi.controlpanel.dto.meal.MealRequestDto;
import com.macabi.controlpanel.dto.meal.MealResponseDto;
import com.macabi.controlpanel.dto.meal.MealUpdateDto;
import com.macabi.controlpanel.model.Meal;
import org.springframework.stereotype.Component;

@Component
public class MealMapper {
    
    public Meal toEntity(MealRequestDto dto) {
        Meal meal = new Meal();
        meal.setName(dto.getName());
        meal.setDescription(dto.getDescription());
        meal.setImageUrl(dto.getImageUrl());
        meal.setClasification(dto.getClasification());
        return meal;
    }
    
    public MealResponseDto toResponseDto(Meal meal) {
        MealResponseDto dto = new MealResponseDto();
        dto.setId(meal.getId());
        dto.setName(meal.getName());
        dto.setDescription(meal.getDescription());
        dto.setImageUrl(meal.getImageUrl());
        dto.setClasification(meal.getClasification());
        return dto;
    }
    
    public void updateEntityFromDto(MealUpdateDto dto, Meal meal) {
        if (dto.getName() != null) {
            meal.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            meal.setDescription(dto.getDescription());
        }
        if (dto.getImageUrl() != null) {
            meal.setImageUrl(dto.getImageUrl());
        }
        if (dto.getClasification() != null) {
            meal.setClasification(dto.getClasification());
        }
    }
}
