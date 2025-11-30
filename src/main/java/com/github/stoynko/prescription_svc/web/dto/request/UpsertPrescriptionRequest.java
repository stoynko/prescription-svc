package com.github.stoynko.prescription_svc.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class UpsertPrescriptionRequest {

    private UUID appointmentId;

    private List<Object> medicaments;

}
