package com.macabi.controlpanel.model;

import com.macabi.controlpanel.model.enums.TypeMenu;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor  
@AllArgsConstructor
@Entity
@Table(name = "booking_meals")
public class BookingMeal extends Booking {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type_menu", nullable = false)
    private TypeMenu typeMenu;
}
