package com.macabi.controlpanel.dto.menu;

import com.macabi.controlpanel.model.enums.TypeMenu;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class MenuRequestDto {
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    @NotNull(message = "Type menu is required")
    private TypeMenu typeMenu;
    
    @NotEmpty(message = "At least one meal is required")
    private List<Long> mealIds;
}
