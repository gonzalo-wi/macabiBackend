package com.macabi.controlpanel.dto.booking;

import com.macabi.controlpanel.model.enums.TypeMenu;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingMealRequestDto {
    
    @NotNull(message = "Participant ID is required")
    private Long participantId;
    
    @NotNull(message = "Meal ID is required")
    private Long mealId;
    
    @NotNull(message = "Type menu is required")
    private TypeMenu typeMenu;
    
    @NotNull(message = "Active status is required")
    private Boolean active = true;
}
