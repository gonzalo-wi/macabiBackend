package com.macabi.controlpanel.dto.transfer;

import com.macabi.controlpanel.model.enums.TypeTransfer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TransferRequestDto {
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotBlank(message = "Origin is required")
    @Size(max = 255, message = "Origin must not exceed 255 characters")
    private String origin;
    
    @NotBlank(message = "Destination is required")
    @Size(max = 255, message = "Destination must not exceed 255 characters")
    private String destination;
    
    @NotNull(message = "Date is required")
    private LocalDateTime date;
    
    @NotNull(message = "Active status is required")
    private Boolean active = true;
    
    @NotNull(message = "Type of transfer is required")
    private TypeTransfer typeTransfer;
    
    @NotNull(message = "Number of reservations is required")
    @PositiveOrZero(message = "Number of reservations must be zero or positive")
    private Integer numberOfReservations = 0;
    
    @NotNull(message = "Suggested buses is required")
    @PositiveOrZero(message = "Suggested buses must be zero or positive")
    private Integer suggestedBuses = 0;
}
