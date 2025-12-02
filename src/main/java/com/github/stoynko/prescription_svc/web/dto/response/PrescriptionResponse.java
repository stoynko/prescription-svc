package com.github.stoynko.prescription_svc.web.dto.response;

import com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionResponse {

    private UUID prescriptionId;

    private UUID appointmentId;

    LocalDateTime createdAt;

    LocalDateTime expiresAt;

    LocalDateTime updatedAt;

    private PrescriptionStatus prescriptionStatus;

    @Builder.Default
    private List<PrescriptionMedicamentResponse> medicaments = List.of();
}
