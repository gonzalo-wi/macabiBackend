package com.macabi.controlpanel.service.iservice;

import com.macabi.controlpanel.dto.booking.BookingTransferRequestDto;
import com.macabi.controlpanel.dto.booking.BookingTransferResponseDto;
import com.macabi.controlpanel.dto.booking.BookingTransferUpdateDto;
import java.util.List;

public interface BookingTransferService {
    
    List<BookingTransferResponseDto> getAllBookingTransfers();
    BookingTransferResponseDto getBookingTransferById(Long id);
    List<BookingTransferResponseDto> getBookingTransfersByParticipantId(Long participantId);
    List<BookingTransferResponseDto> getActiveBookingTransfersByParticipantId(Long participantId);
    List<BookingTransferResponseDto> getBookingTransfersByTransferId(Long transferId);
    List<BookingTransferResponseDto> getActiveBookingTransfersByProjectId(Long projectId);
    BookingTransferResponseDto createBookingTransfer(BookingTransferRequestDto bookingTransferDto);
    BookingTransferResponseDto updateBookingTransfer(Long id, BookingTransferUpdateDto bookingTransferDto);
    void deleteBookingTransfer(Long id);
}
