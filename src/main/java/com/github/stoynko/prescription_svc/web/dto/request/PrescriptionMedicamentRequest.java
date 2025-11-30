package com.github.stoynko.prescription_svc.web.dto.request;

import com.github.stoynko.prescription_svc.model.enums.DosageDelivery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class PrescriptionMedicamentRequest {

    private UUID medicamentId;

    private String dosage;

    private String intakeFrequency;

    private DosageDelivery delivery;

    private int treatmentDuration;

    private String additionalNotes;
}
