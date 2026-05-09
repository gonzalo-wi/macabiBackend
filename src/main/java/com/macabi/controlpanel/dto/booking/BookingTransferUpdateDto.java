package com.macabi.controlpanel.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingTransferUpdateDto {
    
    private Long transferId;
    private Boolean active;
}
