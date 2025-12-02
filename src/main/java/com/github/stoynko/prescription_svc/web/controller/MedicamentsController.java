package com.github.stoynko.prescription_svc.web.controller;

import com.github.stoynko.prescription_svc.service.MedicamentService;
import com.github.stoynko.prescription_svc.web.dto.response.MedicamentItemResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicamentsController {

    private final MedicamentService medicamentService;

    public MedicamentsController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping("/api/v1/medicaments")
    public List<MedicamentItemResponse> getAllMedicaments() {
        return medicamentService.getAllMedicaments();
    }
}
