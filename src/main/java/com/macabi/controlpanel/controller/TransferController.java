package com.macabi.controlpanel.controller;

import com.macabi.controlpanel.dto.transfer.TransferRequestDto;
import com.macabi.controlpanel.dto.transfer.TransferResponseDto;
import com.macabi.controlpanel.dto.transfer.TransferUpdateDto;
import com.macabi.controlpanel.model.enums.TypeTransfer;
import com.macabi.controlpanel.service.iservice.TransferService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
@Tag(name = "Transfers", description = "Transfer management endpoints")
public class TransferController {
    
    private final TransferService transferService;
    
    @GetMapping
    public ResponseEntity<List<TransferResponseDto>> getAllTransfers() {
        List<TransferResponseDto> transfers = transferService.getAllTransfers();
        return ResponseEntity.ok(transfers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDto> getTransferById(@PathVariable Long id) {
        TransferResponseDto transfer = transferService.getTransferById(id);
        return ResponseEntity.ok(transfer);
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TransferResponseDto>> getTransfersByProjectId(@PathVariable Long projectId) {
        List<TransferResponseDto> transfers = transferService.getTransfersByProjectId(projectId);
        return ResponseEntity.ok(transfers);
    }
    
    @GetMapping("/project/{projectId}/active")
    public ResponseEntity<List<TransferResponseDto>> getActiveTransfersByProjectId(@PathVariable Long projectId) {
        List<TransferResponseDto> transfers = transferService.getActiveTransfersByProjectId(projectId);
        return ResponseEntity.ok(transfers);
    }

    @GetMapping("/type/{typeTransfer}")
    public ResponseEntity<List<TransferResponseDto>> getTransfersByTypeTransfer(@PathVariable TypeTransfer typeTransfer) {
        List<TransferResponseDto> transfers = transferService.getTransfersByTypeTransfer(typeTransfer);
        return ResponseEntity.ok(transfers);
    }

    @GetMapping("/project/{projectId}/type/{typeTransfer}")
    public ResponseEntity<List<TransferResponseDto>> getTransfersByProjectIdAndTypeTransfer(
            @PathVariable Long projectId,
            @PathVariable TypeTransfer typeTransfer) {
        List<TransferResponseDto> transfers = transferService.getTransfersByProjectIdAndTypeTransfer(projectId, typeTransfer);
        return ResponseEntity.ok(transfers);
    }

    @PostMapping
    public ResponseEntity<TransferResponseDto> createTransfer(@Valid @RequestBody TransferRequestDto transferDto) {
        TransferResponseDto createdTransfer = transferService.createTransfer(transferDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransfer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TransferResponseDto> updateTransfer(
            @PathVariable Long id,
            @Valid @RequestBody TransferUpdateDto transferDto) {
        TransferResponseDto updatedTransfer = transferService.updateTransfer(id, transferDto);
        return ResponseEntity.ok(updatedTransfer);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable Long id) {
        transferService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }
}
