package com.macabi.controlpanel.exception;

public class DuplicateParticipantException extends RuntimeException {
    
    public DuplicateParticipantException(String message) {
        super(message);
    }
    
    public DuplicateParticipantException(Long projectId, Long userId) {
        super(String.format("User with id %d is already a participant in project with id %d", userId, projectId));
    }
}
