package com.github.stoynko.prescription_svc.web.dto.response;

import com.github.stoynko.prescription_svc.model.enums.DeliveryMethod;
import com.github.stoynko.prescription_svc.model.enums.MedicamentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionMedicamentResponse {

    private UUID id;

    private MedicamentType medicamentType;

    private String brandName;

    private String genericName;

    private String description;

    private DeliveryMethod deliveryMethod;

    private String intakeDosage;

    private String intakeFrequency;

    private String treatmentDuration;

    private String additionalNotes;
}
