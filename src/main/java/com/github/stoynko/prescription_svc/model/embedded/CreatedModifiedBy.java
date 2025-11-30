package com.github.stoynko.prescription_svc.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
public class CreatedModifiedBy implements Serializable {

    @Column(name = "created_by", updatable = false)
    private UUID createdBy;

    @Column(name = "updated_by")
    private UUID updatedBy;
}
