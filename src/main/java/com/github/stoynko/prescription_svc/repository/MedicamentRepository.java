package com.github.stoynko.prescription_svc.repository;

import com.github.stoynko.prescription_svc.model.Medicament;
import com.github.stoynko.prescription_svc.model.enums.MedicamentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, UUID> {

    boolean existsByMedicamentTypeAndBrandName(MedicamentType type, String brandName);
}
