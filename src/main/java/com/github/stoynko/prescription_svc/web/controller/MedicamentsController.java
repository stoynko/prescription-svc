package com.github.stoynko.prescription_svc.web.controller;

import com.github.stoynko.prescription_svc.service.MedicamentService;
import com.github.stoynko.prescription_svc.web.dto.response.MedicamentItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicaments")
@RequiredArgsConstructor
public class MedicamentsController {

    private final MedicamentService medicamentService;

    @GetMapping
    public ResponseEntity<List<MedicamentItemResponse>> getAllMedicaments() {
        return ResponseEntity.ok(medicamentService.getAllMedicaments());
    }
}
