package com.github.stoynko.prescription_svc.service;

import com.github.stoynko.prescription_svc.exception.MedicamentDoesNotExistException;
import com.github.stoynko.prescription_svc.model.PrescriptionMedicament;
import com.github.stoynko.prescription_svc.repository.PrescriptionMedicamentRepository;
import com.github.stoynko.prescription_svc.web.dto.request.RemoveMedicamentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrescriptionMedicamentService {

    private final PrescriptionMedicamentRepository repository;

    public void savePrescriptionMedicament(PrescriptionMedicament medicament) {
        repository.save(medicament);
        log.info("[added-prescription-medicament] Prescription medicament with id %s was successfully saved", medicament.getId());
    }

    @Transactional
    public void removeMedicament(RemoveMedicamentRequest request) {
        PrescriptionMedicament prescribedMedicament = getPrescribedMedicamentById(request.getMedicamentId());
        repository.delete(prescribedMedicament);
        log.info("[deleted-prescription-medicament] Prescription medicament with id %s was successfully deleted", prescribedMedicament.getId());
    }

    public PrescriptionMedicament getPrescribedMedicamentById(UUID medicamentId) {
        return repository.findById(medicamentId).orElseThrow(() -> new MedicamentDoesNotExistException());
    }
}
