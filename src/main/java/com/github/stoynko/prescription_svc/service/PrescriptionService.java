package com.github.stoynko.prescription_svc.service;

import com.github.stoynko.prescription_svc.exception.AppointmentMismatchException;
import com.github.stoynko.prescription_svc.exception.MedicamentDoesNotExistException;
import com.github.stoynko.prescription_svc.exception.PrescriptionAlreadyExistsException;
import com.github.stoynko.prescription_svc.exception.PrescriptionDoesNotExistException;
import com.github.stoynko.prescription_svc.exception.PrescriptionInvalidStatusException;
import com.github.stoynko.prescription_svc.model.Medicament;
import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.model.PrescriptionMedicament;
import com.github.stoynko.prescription_svc.repository.PrescriptionRepository;
import com.github.stoynko.prescription_svc.web.dto.mapper.MedicamentMapper;
import com.github.stoynko.prescription_svc.web.dto.mapper.PrescriptionMapper;
import com.github.stoynko.prescription_svc.web.dto.request.AddMedicamentRequest;
import com.github.stoynko.prescription_svc.web.dto.request.RemoveMedicamentRequest;
import com.github.stoynko.prescription_svc.web.dto.response.PrescriptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus.DRAFT;
import static com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus.ISSUED;
import static com.github.stoynko.prescription_svc.web.dto.mapper.PrescriptionMapper.toPrescriptionResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository repository;
    private final MedicamentService medicamentService;
    private final PrescriptionMedicamentService prescriptionMedicamentService;

    public PrescriptionResponse createPrescription(UUID appointmentId, UUID userId) {

        if (repository.existsPrescriptionByAppointment(appointmentId)) {
            throw new PrescriptionAlreadyExistsException();
        }

        Prescription prescription = PrescriptionMapper.toPrescriptionEntity(appointmentId, userId);
        repository.save(prescription);
        log.info("[prescription-creation] Prescription with id %s was created successfully.".formatted(prescription.getId()));
        return toPrescriptionResponse(prescription);
    }

    public void addMedicament(AddMedicamentRequest request) {

        Prescription prescription = getPrescriptionById(request.getPrescriptionId());

        if (prescription.getPrescriptionStatus() != DRAFT) {
            throw new PrescriptionInvalidStatusException();
        }

        Medicament medicament = medicamentService.getMedicamentById(request.getMedicamentId());
        PrescriptionMedicament prescriptionMedicament = MedicamentMapper.toPrescriptionMedicamentEntity(prescription, medicament, request);
        prescriptionMedicamentService.savePrescriptionMedicament(prescriptionMedicament);

        prescription.getMedicaments().add(prescriptionMedicament);
        repository.save(prescription);
        log.info("[added-medicament] Medicament with id %s was successfully added to prescription %s".formatted(medicament.getId(), prescription.getId()));
    }

    public void issuePrescription(UUID prescriptionId) {
        Prescription prescription = getPrescriptionById(prescriptionId);

        if (prescription.getPrescriptionStatus() != DRAFT) {
            throw new PrescriptionInvalidStatusException();
        }

        prescription.setPrescriptionStatus(ISSUED);
        prescription.setIssuedAt(LocalDateTime.now());
        prescription.setExpiresAt(LocalDateTime.now().plusDays(30));
        prescription.getCreatedModifiedAt().setUpdatedAt(LocalDateTime.now());
        repository.save(prescription);
        log.info("[prescription-issued] Prescription with id %s was issued successfully".formatted(prescription.getId()));
    }

    @Transactional
    public void removeMedicament(UUID appointmentId, RemoveMedicamentRequest request) {

        verifyAppointment(appointmentId, request.getPrescriptionId());

        Prescription prescription = getPrescriptionById(request.getPrescriptionId());

        if (prescription.getPrescriptionStatus() != DRAFT) {
            throw new PrescriptionInvalidStatusException();
        }

        PrescriptionMedicament prescribedMed =
                prescription.getMedicaments()
                        .stream()
                        .filter(pm -> pm.getId().equals(request.getMedicamentId()))
                        .findFirst()
                        .orElseThrow(MedicamentDoesNotExistException::new);

        prescription.getMedicaments().remove(prescribedMed);
        prescriptionMedicamentService.removeMedicamentFromPrescription(prescribedMed);
        log.info("[remove-prescription-medicament] Prescription medicament with id %s was successfully removed".formatted(request.getMedicamentId()));
    }

    public void verifyAppointment(UUID appointmentId, UUID prescriptionId) {

        Prescription prescription = getPrescriptionById(prescriptionId);

        if (!Objects.equals(prescription.getAppointment(), appointmentId)) {
            throw new AppointmentMismatchException();
        }
    }

    public Prescription getById(UUID appointmentId) {
        return repository.findByAppointment(appointmentId).orElseThrow(() -> new PrescriptionDoesNotExistException());
    }

    private Prescription getPrescriptionById(UUID prescriptionId) {
        return repository.findById(prescriptionId).orElseThrow(() -> new PrescriptionDoesNotExistException());
    }
}
