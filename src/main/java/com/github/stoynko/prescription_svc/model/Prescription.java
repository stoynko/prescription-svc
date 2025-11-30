package com.github.stoynko.prescription_svc.model;

import com.github.stoynko.prescription_svc.model.embedded.CreatedModifiedAt;
import com.github.stoynko.prescription_svc.model.embedded.CreatedModifiedBy;
import com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "appointment_id")
    private UUID appointment;

    @OneToMany(mappedBy = "prescription", targetEntity = PrescriptionMedicament.class,
               cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionMedicament> medicaments;

    @Column(name = "prescription_status")
    private PrescriptionStatus status;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Embedded
    @Builder.Default
    private CreatedModifiedBy createdModifiedBy = new CreatedModifiedBy();

    @Embedded
    @Builder.Default
    private CreatedModifiedAt createdModifiedAt = new CreatedModifiedAt();
}
