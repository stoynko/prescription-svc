package com.github.stoynko.prescription_svc.exception;

import static com.github.stoynko.prescription_svc.exception.ErrorMessages.PRESCRIPTION_EXISTS;

public class PrescriptionAlreadyExistsException extends RuntimeException {

  public PrescriptionAlreadyExistsException() {
    super(PRESCRIPTION_EXISTS.getDisplayName());
  }
}
