package com.macabi.controlpanel.dto.transfer;

import com.macabi.controlpanel.model.enums.TypeTransfer;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferUpdateDto {
    
    @Size(max = 255, message = "Origin must not exceed 255 characters")
    private String origin;
    
    @Size(max = 255, message = "Destination must not exceed 255 characters")
    private String destination;
    
    private LocalDateTime date;
    
    private Boolean active;
    
    private TypeTransfer typeTransfer;
    
    @PositiveOrZero(message = "Number of reservations must be zero or positive")
    private Integer numberOfReservations;
    
    @PositiveOrZero(message = "Suggested buses must be zero or positive")
    private Integer suggestedBuses;
}
