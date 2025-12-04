package com.github.stoynko.prescription_svc.exception;

public class PrescriptionInvalidStatusException extends RuntimeException {
    public PrescriptionInvalidStatusException() {
        super(ErrorMessages.PRESCRIPTION_INVALID_STATUS.getDisplayName());
    }
}
