package com.github.stoynko.prescription_svc.web.dto.mapper;

import com.github.stoynko.prescription_svc.model.Medicament;
import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.model.embedded.CreatedModifiedBy;
import com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus;
import com.github.stoynko.prescription_svc.property.MedicamentProperties;
import com.github.stoynko.prescription_svc.utilities.GenerationalUtilities;
import com.github.stoynko.prescription_svc.web.dto.request.UpsertPrescriptionRequest;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.UUID;

import static com.github.stoynko.prescription_svc.model.enums.PrescriptionStatus.DRAFT;
import static com.github.stoynko.prescription_svc.utilities.GenerationalUtilities.extractDigits;

@UtilityClass
public class EntityMapper {

    public static Prescription toPrescriptionEntity(UpsertPrescriptionRequest request, UUID userId) {

        CreatedModifiedBy createdModifiedBy = new CreatedModifiedBy();
        createdModifiedBy.setCreatedBy(userId);
        createdModifiedBy.setUpdatedBy(userId);

        return Prescription.builder()
                .publicId(extractDigits(UUID.randomUUID().toString()))
                .appointment(request.getAppointmentId())
                .medicaments(new ArrayList<>())
                .createdModifiedBy(createdModifiedBy)
                .status(DRAFT)
                .build();
    }

    public static Medicament toMedicamentEntity(MedicamentProperties.MedicamentDetails medicamentDetails) {
        return Medicament.builder()
                .medicamentType(medicamentDetails.getType())
                .brandName(medicamentDetails.getBrandName())
                .genericName(medicamentDetails.getGenericName())
                .deliveryMethods(medicamentDetails.getDeliveryMethods())
                .build();
    }

}
