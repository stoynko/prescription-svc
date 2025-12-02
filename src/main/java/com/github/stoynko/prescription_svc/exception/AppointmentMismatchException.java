package com.github.stoynko.prescription_svc.exception;

public class AppointmentMismatchException extends RuntimeException {

    public AppointmentMismatchException() {
        super(ErrorMessages.APPOINTMENT_MISMATCH.getDisplayName());
    }
}
