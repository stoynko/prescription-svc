package com.github.stoynko.prescription_svc.web.controller;

import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.service.PrescriptionService;
import com.github.stoynko.prescription_svc.web.dto.request.UpsertPrescriptionRequest;
import com.github.stoynko.prescription_svc.web.dto.response.PrescriptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/prescriptions")
public class PrescriptionsController {

    private final PrescriptionService prescriptionService;

    public PrescriptionsController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<PrescriptionResponse> upsertPrescription(@RequestHeader("user-id") UUID userId,
                                                                   @RequestBody UpsertPrescriptionRequest request) {

        Prescription prescription = prescriptionService.upsert(request, userId);
        return null;
    }
}
