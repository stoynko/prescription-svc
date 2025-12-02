package com.github.stoynko.prescription_svc.web.controller;

import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.service.PrescriptionMedicamentService;
import com.github.stoynko.prescription_svc.service.PrescriptionService;
import com.github.stoynko.prescription_svc.web.dto.mapper.PrescriptionMapper;
import com.github.stoynko.prescription_svc.web.dto.request.AddMedicamentRequest;
import com.github.stoynko.prescription_svc.web.dto.response.PrescriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PrescriptionsController {

    private final PrescriptionService prescriptionService;
    private final PrescriptionMedicamentService prescriptionMedicamentService;

    @GetMapping("/api/v1/appointments/{appointmentId}/prescription")
    public ResponseEntity<PrescriptionResponse> getPrescription(@PathVariable UUID appointmentId) {

        Optional<Prescription> optionalPrescription = prescriptionService.findByAppointmentId(appointmentId);

        return optionalPrescription
                .map(PrescriptionMapper::toPrescriptionResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/v1/appointments/{appointmentId}/prescription")
    public ResponseEntity<PrescriptionResponse> createPrescription(@RequestHeader("user-id") UUID userId,
                                                                   @PathVariable UUID appointmentId) {

        PrescriptionResponse prescription = prescriptionService.createPrescription(appointmentId, userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(prescription);
    }

    @PostMapping("/api/v1/appointments/{appointmentId}/prescription/medicaments")
    public ResponseEntity<Void> addMedicamentToPrescription (@RequestHeader("user-id") UUID userId,
                                                             @PathVariable UUID appointmentId,
                                                             @RequestBody AddMedicamentRequest request) {

        prescriptionService.addMedicament(userId, appointmentId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }

}
