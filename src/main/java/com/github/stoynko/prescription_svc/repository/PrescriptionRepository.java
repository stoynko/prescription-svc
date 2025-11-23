package com.github.stoynko.prescription_svc.repository;

import com.github.stoynko.prescription_svc.model.Prescription;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {
}
