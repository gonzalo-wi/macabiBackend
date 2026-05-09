package com.macabi.controlpanel.dto.meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealResponseDto {
    
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String clasification;
}
