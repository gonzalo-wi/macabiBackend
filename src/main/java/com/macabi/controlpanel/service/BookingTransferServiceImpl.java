package com.macabi.controlpanel.service;
import com.macabi.controlpanel.dto.booking.BookingTransferRequestDto;
import com.macabi.controlpanel.dto.booking.BookingTransferResponseDto;
import com.macabi.controlpanel.dto.booking.BookingTransferUpdateDto;
import com.macabi.controlpanel.exception.ResourceNotFoundException;
import com.macabi.controlpanel.mapper.BookingTransferMapper;
import com.macabi.controlpanel.model.BookingTransfer;
import com.macabi.controlpanel.model.Participant;
import com.macabi.controlpanel.model.Transfer;
import com.macabi.controlpanel.repository.BookingTransferRepository;
import com.macabi.controlpanel.repository.ParticipantRepository;
import com.macabi.controlpanel.repository.TransferRepository;
import com.macabi.controlpanel.service.iservice.BookingTransferService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class BookingTransferServiceImpl implements BookingTransferService {
    
    private static final String             BOOKING_TRANSFER_RESOURCE = "BookingTransfer";
    private static final String             PARTICIPANT_RESOURCE      = "Participant";
    private static final String             TRANSFER_RESOURCE         = "Transfer";
    private static final String             ID_FIELD                  = "id";
    private final BookingTransferRepository bookingTransferRepository;
    private final ParticipantRepository     participantRepository;
    private final TransferRepository        transferRepository;
    private final BookingTransferMapper     bookingTransferMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookingTransferResponseDto> getAllBookingTransfers() {
        return bookingTransferRepository.findAll().stream()
                .map(bookingTransferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookingTransferResponseDto getBookingTransferById(Long id) {
        var bookingTransfer = findBookingTransferById(id);
        return bookingTransferMapper.toResponseDto(bookingTransfer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingTransferResponseDto> getBookingTransfersByParticipantId(Long participantId) {
        validateParticipantExists(participantId);
        return bookingTransferRepository.findByParticipantId(participantId).stream()
                .map(bookingTransferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingTransferResponseDto> getActiveBookingTransfersByParticipantId(Long participantId) {
        validateParticipantExists(participantId);
        return bookingTransferRepository.findByParticipantIdAndActive(participantId, true).stream()
                .map(bookingTransferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingTransferResponseDto> getBookingTransfersByTransferId(Long transferId) {
        validateTransferExists(transferId);
        return bookingTransferRepository.findByTransferId(transferId).stream()
                .map(bookingTransferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingTransferResponseDto> getActiveBookingTransfersByProjectId(Long projectId) {
        return bookingTransferRepository.findByProjectIdAndActive(projectId).stream()
                .map(bookingTransferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingTransferResponseDto createBookingTransfer(BookingTransferRequestDto bookingTransferDto) {
        var participant          = findParticipantById(bookingTransferDto.getParticipantId());
        var transfer             = findTransferById(bookingTransferDto.getTransferId());
        var bookingTransfer      = bookingTransferMapper.toEntity(bookingTransferDto, participant, transfer);
        var savedBookingTransfer = bookingTransferRepository.save(bookingTransfer);
        return bookingTransferMapper.toResponseDto(savedBookingTransfer);
    }

    @Override
    public BookingTransferResponseDto updateBookingTransfer(Long id, BookingTransferUpdateDto bookingTransferDto) {
        var bookingTransfer = findBookingTransferById(id);
        Transfer transfer = null;
        if (bookingTransferDto.getTransferId() != null) {
            transfer = findTransferById(bookingTransferDto.getTransferId());
        }
        bookingTransferMapper.updateEntityFromDto(bookingTransferDto, bookingTransfer, transfer);
        var updatedBookingTransfer = bookingTransferRepository.save(bookingTransfer);
        return bookingTransferMapper.toResponseDto(updatedBookingTransfer);
    }

    @Override
    public void deleteBookingTransfer(Long id) {
        var bookingTransfer = findBookingTransferById(id);
        bookingTransferRepository.delete(bookingTransfer);
    }
    
    // Metodos privados
    
    private BookingTransfer findBookingTransferById(Long id) {
        return bookingTransferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        BOOKING_TRANSFER_RESOURCE, ID_FIELD, id));
    }
    
    private Participant findParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        PARTICIPANT_RESOURCE, ID_FIELD, id));
    }
    
    private Transfer findTransferById(Long id) {
        return transferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        TRANSFER_RESOURCE, ID_FIELD, id));
    }
    
    private void validateParticipantExists(Long participantId) {
        if (!participantRepository.existsById(participantId)) {
            throw new ResourceNotFoundException(PARTICIPANT_RESOURCE, ID_FIELD, participantId);
        }
    }
    
    private void validateTransferExists(Long transferId) {
        if (!transferRepository.existsById(transferId)) {
            throw new ResourceNotFoundException(TRANSFER_RESOURCE, ID_FIELD, transferId);
        }
    }
}
