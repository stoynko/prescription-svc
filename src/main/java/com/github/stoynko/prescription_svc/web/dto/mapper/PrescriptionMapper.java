package com.github.stoynko.prescription_svc.web.dto.mapper;

import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.model.embedded.CreatedModifiedBy;
import com.github.stoynko.prescription_svc.web.dto.response.PrescriptionMedicamentResponse;
import com.github.stoynko.prescription_svc.web.dto.response.PrescriptionResponse;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus.DRAFT;
import static com.github.stoynko.prescription_svc.utilities.GenerationalUtilities.extractDigits;

@UtilityClass
public class PrescriptionMapper {

    public static Prescription toPrescriptionEntity(UUID appointmentId, UUID userId) {

        CreatedModifiedBy createdModifiedBy = new CreatedModifiedBy();
        createdModifiedBy.setCreatedBy(userId);
        createdModifiedBy.setUpdatedBy(userId);

        return Prescription.builder()
                .publicId(extractDigits(UUID.randomUUID().toString()))
                .appointment(appointmentId)
                .medicaments(new ArrayList<>())
                .createdModifiedBy(createdModifiedBy)
                .prescriptionStatus(DRAFT)
                .build();
    }

    public static PrescriptionResponse toPrescriptionResponse(Prescription prescription) {

        List<PrescriptionMedicamentResponse> medicamentResponses =
                prescription.getMedicaments() == null
                        ? List.of()
                        : prescription.getMedicaments().stream()
                        .map(MedicamentMapper::toPrescriptionMedicamentResponse)
                        .toList();

        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getId())
                .appointmentId(prescription.getAppointment())
                .createdAt(prescription.getCreatedModifiedAt().getCreatedAt())
                .issuedAt(prescription.getIssuedAt())
                .expiresAt(prescription.getExpiresAt())
                .prescriptionStatus(prescription.getPrescriptionStatus())
                .medicaments(medicamentResponses)
                .build();
    }
}
