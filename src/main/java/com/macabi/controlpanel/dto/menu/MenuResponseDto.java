package com.macabi.controlpanel.dto.menu;

import com.macabi.controlpanel.model.enums.TypeMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponseDto {
    
    private Long id;
    private Long projectId;
    private String projectName;
    private LocalDate date;
    private TypeMenu typeMenu;
    private boolean active;
    private List<MealSummaryDto> meals;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MealSummaryDto {
        private Long id;
        private String name;
        private String description;
        private String imageUrl;
        private String clasification;
    }
}
