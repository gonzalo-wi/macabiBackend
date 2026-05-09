package com.macabi.controlpanel.controller;

import com.macabi.controlpanel.dto.booking.BookingTransferRequestDto;
import com.macabi.controlpanel.dto.booking.BookingTransferResponseDto;
import com.macabi.controlpanel.dto.booking.BookingTransferUpdateDto;
import com.macabi.controlpanel.service.iservice.BookingTransferService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/booking-transfers")
@RequiredArgsConstructor
@Tag(name = "Booking Transfers", description = "Transfer booking management endpoints")
public class BookingTransferController {
    
    private final BookingTransferService bookingTransferService;
    
    @GetMapping
    public ResponseEntity<List<BookingTransferResponseDto>> getAllBookingTransfers() {
        List<BookingTransferResponseDto> bookingTransfers = bookingTransferService.getAllBookingTransfers();
        return ResponseEntity.ok(bookingTransfers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BookingTransferResponseDto> getBookingTransferById(@PathVariable Long id) {
        BookingTransferResponseDto bookingTransfer = bookingTransferService.getBookingTransferById(id);
        return ResponseEntity.ok(bookingTransfer);
    }
    
    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<BookingTransferResponseDto>> getBookingTransfersByParticipantId(@PathVariable Long participantId) {
        List<BookingTransferResponseDto> bookingTransfers = bookingTransferService.getBookingTransfersByParticipantId(participantId);
        return ResponseEntity.ok(bookingTransfers);
    }
    
    @GetMapping("/participant/{participantId}/active")
    public ResponseEntity<List<BookingTransferResponseDto>> getActiveBookingTransfersByParticipantId(@PathVariable Long participantId) {
        List<BookingTransferResponseDto> bookingTransfers = bookingTransferService.getActiveBookingTransfersByParticipantId(participantId);
        return ResponseEntity.ok(bookingTransfers);
    }
    
    @GetMapping("/transfer/{transferId}")
    public ResponseEntity<List<BookingTransferResponseDto>> getBookingTransfersByTransferId(@PathVariable Long transferId) {
        List<BookingTransferResponseDto> bookingTransfers = bookingTransferService.getBookingTransfersByTransferId(transferId);
        return ResponseEntity.ok(bookingTransfers);
    }
    
    @GetMapping("/project/{projectId}/active")
    public ResponseEntity<List<BookingTransferResponseDto>> getActiveBookingTransfersByProjectId(@PathVariable Long projectId) {
        List<BookingTransferResponseDto> bookingTransfers = bookingTransferService.getActiveBookingTransfersByProjectId(projectId);
        return ResponseEntity.ok(bookingTransfers);
    }
    
    @PostMapping
    public ResponseEntity<BookingTransferResponseDto> createBookingTransfer(@Valid @RequestBody BookingTransferRequestDto bookingTransferDto) {
        BookingTransferResponseDto createdBookingTransfer = bookingTransferService.createBookingTransfer(bookingTransferDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookingTransfer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BookingTransferResponseDto> updateBookingTransfer(
            @PathVariable Long id,
            @Valid @RequestBody BookingTransferUpdateDto bookingTransferDto) {
        BookingTransferResponseDto updatedBookingTransfer = bookingTransferService.updateBookingTransfer(id, bookingTransferDto);
        return ResponseEntity.ok(updatedBookingTransfer);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingTransfer(@PathVariable Long id) {
        bookingTransferService.deleteBookingTransfer(id);
        return ResponseEntity.noContent().build();
    }
}
