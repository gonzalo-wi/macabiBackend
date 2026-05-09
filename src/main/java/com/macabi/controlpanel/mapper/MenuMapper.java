package com.macabi.controlpanel.mapper;

import com.macabi.controlpanel.dto.menu.MenuRequestDto;
import com.macabi.controlpanel.dto.menu.MenuResponseDto;
import com.macabi.controlpanel.dto.menu.MenuUpdateDto;
import com.macabi.controlpanel.model.Meal;
import com.macabi.controlpanel.model.Menu;
import com.macabi.controlpanel.model.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuMapper {
    
    /**
     * Convert Menu entity to MenuResponseDto
     */
    public MenuResponseDto toResponseDto(Menu menu) {
        if (menu == null) {
            return null;
        }
        
        MenuResponseDto dto = new MenuResponseDto();
        dto.setId(menu.getId());
        dto.setProjectId(menu.getProject().getId());
        dto.setProjectName(menu.getProject().getName());
        dto.setDate(menu.getDate());
        dto.setTypeMenu(menu.getTypeMenu());
        dto.setActive(menu.isActive());
        
        // Convert meals to MealSummaryDto
        List<MenuResponseDto.MealSummaryDto> mealSummaries = menu.getMeals().stream()
                .map(this::toMealSummaryDto)
                .collect(Collectors.toList());
        dto.setMeals(mealSummaries);
        
        return dto;
    }
    
    /**
     * Convert MenuRequestDto to Menu entity
     */
    public Menu toEntity(MenuRequestDto dto, Project project, List<Meal> meals) {
        if (dto == null) {
            return null;
        }
        
        Menu menu = new Menu();
        menu.setProject(project);
        menu.setDate(dto.getDate());
        menu.setTypeMenu(dto.getTypeMenu());
        menu.setActive(true);
        menu.setMeals(meals != null ? meals : new ArrayList<>());
        
        return menu;
    }
    
    /**
     * Update existing Menu entity from MenuUpdateDto
     */
    public void updateEntityFromDto(MenuUpdateDto dto, Menu menu, List<Meal> meals) {
        if (dto == null || menu == null) {
            return;
        }
        
        if (dto.getDate() != null) {
            menu.setDate(dto.getDate());
        }
        
        if (dto.getTypeMenu() != null) {
            menu.setTypeMenu(dto.getTypeMenu());
        }
        
        if (dto.getActive() != null) {
            menu.setActive(dto.getActive());
        }
        
        if (meals != null) {
            menu.setMeals(meals);
        }
    }
    
    /**
     * Convert Meal entity to MealSummaryDto
     */
    private MenuResponseDto.MealSummaryDto toMealSummaryDto(Meal meal) {
        if (meal == null) {
            return null;
        }
        
        return new MenuResponseDto.MealSummaryDto(
                meal.getId(),
                meal.getName(),
                meal.getDescription(),
                meal.getImageUrl(),
                meal.getClasification()
        );
    }
}
