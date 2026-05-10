package com.macabi.controlpanel.service;

import com.macabi.controlpanel.dto.transfer.TransferRequestDto;
import com.macabi.controlpanel.dto.transfer.TransferResponseDto;
import com.macabi.controlpanel.dto.transfer.TransferUpdateDto;
import com.macabi.controlpanel.exception.ResourceNotFoundException;
import com.macabi.controlpanel.mapper.TransferMapper;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.Transfer;
import com.macabi.controlpanel.model.enums.TypeTransfer;
import com.macabi.controlpanel.repository.ProjectRepository;
import com.macabi.controlpanel.repository.TransferRepository;
import com.macabi.controlpanel.service.iservice.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    
    private static final String      TRANSFER_RESOURCE = "Transfer";
    private static final String      PROJECT_RESOURCE  = "Project";
    private static final String      ID_FIELD          = "id";
    private final TransferRepository transferRepository;
    private final ProjectRepository  projectRepository;
    private final TransferMapper     transferMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TransferResponseDto> getAllTransfers() {
        return transferRepository.findAll().stream()
                .map(transferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TransferResponseDto getTransferById(Long id) {
        var transfer = findTransferById(id);
        return transferMapper.toResponseDto(transfer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransferResponseDto> getTransfersByProjectId(Long projectId) {
        validateProjectExists(projectId);
        return transferRepository.findByProjectId(projectId).stream()
                .map(transferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransferResponseDto> getActiveTransfersByProjectId(Long projectId) {
        validateProjectExists(projectId);
        return transferRepository.findByProjectIdAndActive(projectId, true).stream()
                .map(transferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransferResponseDto createTransfer(TransferRequestDto transferDto) {
        var project       = findProjectById(transferDto.getProjectId());
        var transfer      = transferMapper.toEntity(transferDto, project);
        var savedTransfer = transferRepository.save(transfer);
        return transferMapper.toResponseDto(savedTransfer);
    }

    @Override
    public TransferResponseDto updateTransfer(Long id, TransferUpdateDto transferDto) {
        var transfer        = findTransferById(id);
        transferMapper.updateEntityFromDto(transferDto, transfer);
        var updatedTransfer = transferRepository.save(transfer);
        return transferMapper.toResponseDto(updatedTransfer);
    }

    @Override
    public void deleteTransfer(Long id) {
        var transfer = findTransferById(id);
        transferRepository.delete(transfer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransferResponseDto> getTransfersByTypeTransfer(TypeTransfer typeTransfer) {
        return transferRepository.findByTypeTransfer(typeTransfer).stream()
                .map(transferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransferResponseDto> getTransfersByProjectIdAndTypeTransfer(Long projectId, TypeTransfer typeTransfer) {
        validateProjectExists(projectId);
        return transferRepository.findByProjectIdAndTypeTransfer(projectId, typeTransfer).stream()
                .map(transferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // Metodos Privados 
    
    private Transfer findTransferById(Long id) {
        return transferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        TRANSFER_RESOURCE, ID_FIELD, id));
    }
    
    private Project findProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        PROJECT_RESOURCE, ID_FIELD, id));
    }
    
    private void validateProjectExists(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException(PROJECT_RESOURCE, ID_FIELD, projectId);
        }
    }
}
