package com.github.stoynko.prescription_svc.service;

import com.github.stoynko.prescription_svc.exception.AppointmentMismatchException;
import com.github.stoynko.prescription_svc.exception.PrescriptionAlreadyExists;
import com.github.stoynko.prescription_svc.exception.PrescriptionDoesNotExistException;
import com.github.stoynko.prescription_svc.model.Medicament;
import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.model.PrescriptionMedicament;
import com.github.stoynko.prescription_svc.repository.PrescriptionRepository;
import com.github.stoynko.prescription_svc.web.dto.mapper.MedicamentMapper;
import com.github.stoynko.prescription_svc.web.dto.mapper.PrescriptionMapper;
import com.github.stoynko.prescription_svc.web.dto.request.AddMedicamentRequest;
import com.github.stoynko.prescription_svc.web.dto.response.PrescriptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
            throw new PrescriptionAlreadyExists();
        }

        Prescription prescription = PrescriptionMapper.toPrescriptionEntity(appointmentId, userId);
        repository.save(prescription);
        log.info("[prescription-creation] Prescription with id %s was created successfully.".formatted(prescription.getId()));
        return toPrescriptionResponse(prescription);
    }

    public void addMedicament(UUID userId, UUID appointmentId, AddMedicamentRequest request) {

        verifyAppointment(appointmentId, request.getPrescriptionId());

        Prescription prescription = getPrescriptionById(request.getPrescriptionId());
        Medicament medicament = medicamentService.getMedicamentById(request.getMedicamentId());
        PrescriptionMedicament prescriptionMedicament = MedicamentMapper.toPrescriptionMedicamentEntity(prescription, medicament, request);
        prescriptionMedicamentService.savePrescriptionMedicament(prescriptionMedicament);

        prescription.getMedicaments().add(prescriptionMedicament);
        prescription.getCreatedModifiedBy().setUpdatedBy(userId);
        repository.save(prescription);
        log.info("[added-medicament] Medicament with id %s was successfully added to prescription %s", medicament.getId(), request.getPrescriptionId());
    }

    public void verifyAppointment(UUID appointmentId, UUID prescriptionId) {

        Prescription prescription = getPrescriptionById(prescriptionId);

        if (!Objects.equals(prescription.getAppointment(), appointmentId)) {
            throw new AppointmentMismatchException();
        }
    }

    public Optional<Prescription> findByAppointmentId(UUID appointmentId) {
        return repository.findByAppointment(appointmentId);
    }

    private Prescription getPrescriptionById(UUID prescriptionId) {
        return repository.findById(prescriptionId).orElseThrow(() -> new PrescriptionDoesNotExistException());
    }
}
