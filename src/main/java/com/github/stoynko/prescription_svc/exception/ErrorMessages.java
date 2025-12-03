package com.github.stoynko.prescription_svc.exception;

public enum ErrorMessages {

    PRESCRIPTION_EXISTS("Prescription for this appointment already exists"),
    MEDICAMENT_NOT_FOUND("Medicament with this id does not exist"),
    PRESCRIPTION_NOT_FOUND("Prescription with this id does not exist"),
    APPOINTMENT_MISMATCH("The prescription id does not match with the provided appointment id"),
    PRESCRIPTION_INVALID_STATUS("The prescription is already issued and no further changes are possible");

    private String displayName;

    ErrorMessages(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
