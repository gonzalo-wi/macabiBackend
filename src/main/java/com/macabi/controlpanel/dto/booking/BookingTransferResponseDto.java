package com.macabi.controlpanel.dto.booking;

import com.macabi.controlpanel.dto.transfer.TransferResponseDto;
import com.macabi.controlpanel.model.enums.TypeBooking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingTransferResponseDto {
    
    private Long id;
    private ParticipantSummaryDto participant;
    private TransferResponseDto transfer;
    private TypeBooking typeBooking;
    private boolean active;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParticipantSummaryDto {
        private Long id;
        private String name;
        private String firstName;
        private String email;
        private Long projectId;
        private String projectName;
    }
}
