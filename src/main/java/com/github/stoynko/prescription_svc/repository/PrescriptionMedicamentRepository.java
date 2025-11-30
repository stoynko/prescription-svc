package com.github.stoynko.prescription_svc.repository;

import com.github.stoynko.prescription_svc.model.PrescriptionMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrescriptionMedicamentRepository extends JpaRepository<PrescriptionMedicament, UUID> {

}
