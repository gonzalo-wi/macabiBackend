package com.macabi.controlpanel.dto.transfer;

import com.macabi.controlpanel.dto.project.ProjectSummaryDto;
import com.macabi.controlpanel.model.enums.TypeTransfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseDto {
    
    private Long id;
    private ProjectSummaryDto project;
    private String origin;
    private String destination;
    private LocalDateTime date;
    private boolean active;
    private TypeTransfer typeTransfer;
    private int numberOfReservations;
    private int suggestedBuses;
}
