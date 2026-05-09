package com.macabi.controlpanel.mapper;

import com.macabi.controlpanel.dto.booking.BookingTransferRequestDto;
import com.macabi.controlpanel.dto.booking.BookingTransferResponseDto;
import com.macabi.controlpanel.dto.booking.BookingTransferUpdateDto;
import com.macabi.controlpanel.model.BookingTransfer;
import com.macabi.controlpanel.model.Participant;
import com.macabi.controlpanel.model.Transfer;
import com.macabi.controlpanel.model.enums.TypeBooking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingTransferMapper {
    
    private final TransferMapper transferMapper;
    
    public BookingTransfer toEntity(BookingTransferRequestDto dto, Participant participant, Transfer transfer) {
        BookingTransfer bookingTransfer = new BookingTransfer();
        bookingTransfer.setParticipant(participant);
        bookingTransfer.setTransfer(transfer);
        bookingTransfer.setTypeBooking(TypeBooking.TRANSFER);
        bookingTransfer.setActive(dto.getActive());
        return bookingTransfer;
    }
    
    public BookingTransferResponseDto toResponseDto(BookingTransfer bookingTransfer) {
        BookingTransferResponseDto dto = new BookingTransferResponseDto();
        dto.setId(bookingTransfer.getId());
        dto.setTypeBooking(bookingTransfer.getTypeBooking());
        dto.setActive(bookingTransfer.isActive());
        Participant participant = bookingTransfer.getParticipant();
        BookingTransferResponseDto.ParticipantSummaryDto participantDto = 
            new BookingTransferResponseDto.ParticipantSummaryDto();
        participantDto.setId(participant.getId());
        participantDto.setName(participant.getUser().getName());
        participantDto.setFirstName(participant.getUser().getFirstName());
        participantDto.setEmail(participant.getUser().getEmail());
        participantDto.setProjectId(participant.getProject().getId());
        participantDto.setProjectName(participant.getProject().getName());
        dto.setParticipant(participantDto);
        dto.setTransfer(transferMapper.toResponseDto(bookingTransfer.getTransfer()));
        return dto;
    }
    
    public void updateEntityFromDto(BookingTransferUpdateDto dto, BookingTransfer bookingTransfer, Transfer transfer) {
        if (dto.getTransferId() != null && transfer != null) {
            bookingTransfer.setTransfer(transfer);
        }
        if (dto.getActive() != null) {
            bookingTransfer.setActive(dto.getActive());
        }
    }
}
