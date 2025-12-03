package com.github.stoynko.prescription_svc.job;

import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus.EXPIRED;
import static com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus.ISSUED;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrescriptionExpirationScheduler {

    private final PrescriptionRepository repository;

    @Transactional
    @Scheduled(cron = "0 30 * * * *")
    public void markPrescriptionsAsExpired() {

        LocalDateTime now = LocalDateTime.now();
        List<Prescription> expiredPrescriptions = repository.findByPrescriptionStatusAndExpiresAtBefore(ISSUED, now);

        if (expiredPrescriptions.isEmpty()) {
            return;
        }

        for (Prescription expiredPrescription : expiredPrescriptions) {
            expiredPrescription.setPrescriptionStatus(EXPIRED);
            expiredPrescription.getCreatedModifiedAt().setUpdatedAt(now);
        }

        log.info("[prescription-expiry] Marked %d prescriptions as EXPIRED at %s", expiredPrescriptions.size(), now);
    }
}
