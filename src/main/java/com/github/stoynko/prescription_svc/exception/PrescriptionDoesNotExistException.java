package com.github.stoynko.prescription_svc.exception;

public class PrescriptionDoesNotExistException extends RuntimeException {

    public PrescriptionDoesNotExistException() {
        super(ErrorMessages.PRESCRIPTION_NOT_FOUND.getDisplayName());
    }
}
