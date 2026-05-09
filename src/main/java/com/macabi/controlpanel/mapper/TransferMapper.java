package com.macabi.controlpanel.mapper;

import com.macabi.controlpanel.dto.transfer.TransferRequestDto;
import com.macabi.controlpanel.dto.transfer.TransferResponseDto;
import com.macabi.controlpanel.dto.transfer.TransferUpdateDto;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferMapper {
    
    private final ProjectMapper projectMapper;
    
    public Transfer toEntity(TransferRequestDto dto, Project project) {
        Transfer transfer = new Transfer();
        transfer.setProject(project);
        transfer.setOrigin(dto.getOrigin());
        transfer.setDestination(dto.getDestination());
        transfer.setDate(dto.getDate());
        transfer.setActive(dto.getActive());
        transfer.setTypeTransfer(dto.getTypeTransfer());
        transfer.setNumberOfReservations(dto.getNumberOfReservations());
        transfer.setSuggestedBuses(dto.getSuggestedBuses());
        return transfer;
    }
    
    public TransferResponseDto toResponseDto(Transfer transfer) {
        TransferResponseDto dto = new TransferResponseDto();
        dto.setId(transfer.getId());
        dto.setProject(projectMapper.toSummaryDto(transfer.getProject()));
        dto.setOrigin(transfer.getOrigin());
        dto.setDestination(transfer.getDestination());
        dto.setDate(transfer.getDate());
        dto.setActive(transfer.isActive());
        dto.setTypeTransfer(transfer.getTypeTransfer());
        dto.setNumberOfReservations(transfer.getNumberOfReservations());
        dto.setSuggestedBuses(transfer.getSuggestedBuses());
        return dto;
    }
    
    public void updateEntityFromDto(TransferUpdateDto dto, Transfer transfer) {
        if (dto.getOrigin() != null) {
            transfer.setOrigin(dto.getOrigin());
        }
        if (dto.getDestination() != null) {
            transfer.setDestination(dto.getDestination());
        }
        if (dto.getDate() != null) {
            transfer.setDate(dto.getDate());
        }
        if (dto.getActive() != null) {
            transfer.setActive(dto.getActive());
        }
        if (dto.getTypeTransfer() != null) {
            transfer.setTypeTransfer(dto.getTypeTransfer());
        }
        if (dto.getNumberOfReservations() != null) {
            transfer.setNumberOfReservations(dto.getNumberOfReservations());
        }
        if (dto.getSuggestedBuses() != null) {
            transfer.setSuggestedBuses(dto.getSuggestedBuses());
        }
    }
}
