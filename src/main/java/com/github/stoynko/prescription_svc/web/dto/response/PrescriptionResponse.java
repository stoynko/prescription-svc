package com.github.stoynko.prescription_svc.web.dto.response;

import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.model.PrescriptionMedicament;
import com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class PrescriptionResponse {

    private UUID id;

    private String publicId;

    private UUID appointmentId;

    private PrescriptionStatus prescriptionStatus;

    //private List<PrescriptionMedicamentResponse> medicaments;

}
