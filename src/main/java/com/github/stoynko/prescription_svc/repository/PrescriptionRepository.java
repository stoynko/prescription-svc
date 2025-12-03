package com.github.stoynko.prescription_svc.repository;

import com.github.stoynko.prescription_svc.model.Prescription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {

    Optional<Prescription> findByAppointment(UUID appointmentId);

    boolean existsPrescriptionByAppointment(UUID appointmentId);

    List<Prescription> findByPrescriptionStatusAndExpiresAtBefore(PrescriptionStatus status, LocalDateTime before);
}
