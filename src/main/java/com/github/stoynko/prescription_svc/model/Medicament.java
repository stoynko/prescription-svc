package com.github.stoynko.prescription_svc.model;

import com.github.stoynko.prescription_svc.model.enums.DeliveryMethod;
import com.github.stoynko.prescription_svc.model.enums.MedicamentType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicaments")
public class Medicament {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "medicament_type", nullable = false)
    private MedicamentType medicamentType;

    @Column(name = "description")
    private String description;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "generic_name")
    private String genericName;

    @ElementCollection(targetClass = DeliveryMethod.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "medicament_delivery_methods",
                     joinColumns = @JoinColumn(name = "medicament_id"))
    @Column(name = "delivery_method")
    private Set<DeliveryMethod> deliveryMethods;
}
