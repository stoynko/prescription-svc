package com.github.stoynko.prescription_svc.web.controller;

import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.service.PrescriptionMedicamentService;
import com.github.stoynko.prescription_svc.service.PrescriptionService;
import com.github.stoynko.prescription_svc.web.dto.mapper.PrescriptionMapper;
import com.github.stoynko.prescription_svc.web.dto.request.AddMedicamentRequest;
import com.github.stoynko.prescription_svc.web.dto.request.RemoveMedicamentRequest;
import com.github.stoynko.prescription_svc.web.dto.response.PrescriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class PrescriptionsController {

    private final PrescriptionService prescriptionService;
    private final PrescriptionMedicamentService prescriptionMedicamentService;

    @GetMapping("/{appointmentId}/prescription")
    public ResponseEntity<PrescriptionResponse> getPrescription(@PathVariable UUID appointmentId) {
        Prescription prescription = prescriptionService.getById(appointmentId);
        return ResponseEntity
                .ok(PrescriptionMapper.toPrescriptionResponse(prescription));

    }

    @PostMapping("/{appointmentId}/prescription")
    public ResponseEntity<PrescriptionResponse> createPrescription(@RequestHeader("user-id") UUID userId,
                                                                   @PathVariable UUID appointmentId) {

        PrescriptionResponse prescription = prescriptionService.createPrescription(appointmentId, userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(prescription);
    }

    @PostMapping("/{appointmentId}/prescription/medicaments")
    public ResponseEntity<Void> addMedicamentToPrescription (@RequestHeader("user-id") UUID userId,
                                                             @PathVariable UUID appointmentId,
                                                             @RequestBody AddMedicamentRequest request) {

        prescriptionService.verifyAppointment(appointmentId, request.getPrescriptionId());
        prescriptionService.addMedicament(request);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{appointmentId}/prescription/medicaments/remove")
    public ResponseEntity<Void> removeMedicamentFromPrescription (@PathVariable UUID appointmentId,
                                                                  @RequestBody RemoveMedicamentRequest request) {

        prescriptionService.removeMedicament(appointmentId, request);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{appointmentId}/prescription/issue")
    public ResponseEntity<Void> issuePrescription(@PathVariable UUID appointmentId,
                                                  @RequestBody UUID prescriptionId) {

        prescriptionService.verifyAppointment(appointmentId, prescriptionId);

        prescriptionService.issuePrescription(prescriptionId);
        return ResponseEntity
                .noContent()
                .build();
    }

}
