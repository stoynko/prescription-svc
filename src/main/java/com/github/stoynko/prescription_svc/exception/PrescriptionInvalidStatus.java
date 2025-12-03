package com.github.stoynko.prescription_svc.exception;

public class PrescriptionInvalidStatus extends RuntimeException {
    public PrescriptionInvalidStatus() {
        super(ErrorMessages.PRESCRIPTION_INVALID_STATUS.getDisplayName());
    }
}
