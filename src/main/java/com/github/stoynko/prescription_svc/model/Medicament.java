package com.github.stoynko.prescription_svc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicaments")
public class Medicament {

    @Id
    @UUID
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Prescription prescription;

    private String medicamentName;

    private String dosage;

    private int frequency;

    private int treatmentDuration;

    private String intake;

    private String additionalInstructions;

    private int quantity;
}
