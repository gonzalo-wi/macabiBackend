package com.macabi.controlpanel.dto.booking;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingTransferRequestDto {
    
    @NotNull(message = "Participant ID is required")
    private Long participantId;
    
    @NotNull(message = "Transfer ID is required")
    private Long transferId;
    
    @NotNull(message = "Active status is required")
    private Boolean active = true;
}
