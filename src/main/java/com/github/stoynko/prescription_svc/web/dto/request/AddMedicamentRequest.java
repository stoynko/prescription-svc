package com.github.stoynko.prescription_svc.web.dto.request;

import com.github.stoynko.prescription_svc.model.enums.DeliveryMethod;
import com.github.stoynko.prescription_svc.model.enums.MedicamentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AddMedicamentRequest {

    private UUID medicamentId;

    private UUID prescriptionId;

    private MedicamentType medicamentType;

    private DeliveryMethod deliveryMethod;

    private String intakeFrequency;

    private String intakeDosage;

    private String treatmentDuration;

    private String additionalNotes;
}
