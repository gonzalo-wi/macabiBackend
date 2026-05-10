package com.macabi.controlpanel.service.iservice;

import com.macabi.controlpanel.dto.transfer.TransferRequestDto;
import com.macabi.controlpanel.dto.transfer.TransferResponseDto;
import com.macabi.controlpanel.dto.transfer.TransferUpdateDto;
import com.macabi.controlpanel.model.enums.TypeTransfer;
import java.util.List;

public interface TransferService {
    
    List<TransferResponseDto> getAllTransfers();
    TransferResponseDto getTransferById(Long id);
    List<TransferResponseDto> getTransfersByProjectId(Long projectId);
    List<TransferResponseDto> getActiveTransfersByProjectId(Long projectId);
    List<TransferResponseDto> getTransfersByTypeTransfer(TypeTransfer typeTransfer);
    List<TransferResponseDto> getTransfersByProjectIdAndTypeTransfer(Long projectId, TypeTransfer typeTransfer);
    TransferResponseDto createTransfer(TransferRequestDto transferDto);
    TransferResponseDto updateTransfer(Long id, TransferUpdateDto transferDto);
    void deleteTransfer(Long id);
}
