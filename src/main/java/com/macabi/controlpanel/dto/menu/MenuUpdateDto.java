package com.macabi.controlpanel.dto.menu;

import com.macabi.controlpanel.model.enums.TypeMenu;
import jakarta.validation.constraints.NotEmpty;
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
public class MenuUpdateDto {
    
    private LocalDate date;
    private TypeMenu typeMenu;
    private Boolean active;
    
    @NotEmpty(message = "At least one meal is required")
    private List<Long> mealIds;
}
