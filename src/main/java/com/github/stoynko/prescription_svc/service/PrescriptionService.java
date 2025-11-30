package com.github.stoynko.prescription_svc.service;

import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.repository.PrescriptionRepository;
import com.github.stoynko.prescription_svc.web.dto.mapper.EntityMapper;
import com.github.stoynko.prescription_svc.web.dto.request.UpsertPrescriptionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository repository;

    public Prescription upsert(UpsertPrescriptionRequest request, UUID userId) {

        Prescription prescription = EntityMapper.toPrescriptionEntity(request, userId);
        repository.save(prescription);
        log.info("[prescription-upsert] Prescription with id %s was upserted.".formatted(prescription.getId()));
        return prescription;
    }

}
