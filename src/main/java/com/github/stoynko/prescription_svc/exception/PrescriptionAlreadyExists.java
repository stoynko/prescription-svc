package com.github.stoynko.prescription_svc.exception;

import static com.github.stoynko.prescription_svc.exception.ErrorMessages.PRESCRIPTION_EXISTS;

public class PrescriptionAlreadyExists extends RuntimeException {

  public PrescriptionAlreadyExists() {
    super(PRESCRIPTION_EXISTS.getDisplayName());
  }
}
