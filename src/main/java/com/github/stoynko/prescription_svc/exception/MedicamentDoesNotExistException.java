package com.github.stoynko.prescription_svc.exception;

public class MedicamentDoesNotExistException extends RuntimeException
{
    public MedicamentDoesNotExistException() {
        super(ErrorMessages.MEDICAMENT_NOT_FOUND.getDisplayName());
    }
}
