package com.macabi.controlpanel.dto.booking;

import com.macabi.controlpanel.model.enums.TypeMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingMealUpdateDto {
    
    private Long mealId;
    private TypeMenu typeMenu;
    private Boolean active;
}
