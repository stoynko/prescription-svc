package com.github.stoynko.prescription_svc.web.dto.response;

import com.github.stoynko.prescription_svc.model.enums.DeliveryMethod;
import com.github.stoynko.prescription_svc.model.enums.MedicamentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentItemResponse {

    UUID medicamentId;

    MedicamentType type;

    String description;

    String brandName;

    String genericName;

    Set<DeliveryMethod> deliveryMethods;
}
