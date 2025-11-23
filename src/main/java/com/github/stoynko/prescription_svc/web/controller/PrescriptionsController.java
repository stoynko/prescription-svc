package com.github.stoynko.prescription_svc.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriptionsController {

    @PostMapping("/api/prescriptions")
    public void createPrescription() {

    }
}
