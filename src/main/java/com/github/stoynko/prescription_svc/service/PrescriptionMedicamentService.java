package com.github.stoynko.prescription_svc.service;

import com.github.stoynko.prescription_svc.model.PrescriptionMedicament;
import com.github.stoynko.prescription_svc.repository.PrescriptionMedicamentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrescriptionMedicamentService {

    private final PrescriptionMedicamentRepository repository;

    public void savePrescriptionMedicament(PrescriptionMedicament medicament) {
        repository.save(medicament);
        log.info("[added-prescription-medicament] Prescription medicament with id %s was successfully saved", medicament.getId());
    }


}
