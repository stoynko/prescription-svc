package com.github.stoynko.prescription_svc.web.dto.mapper;

import com.github.stoynko.prescription_svc.model.Medicament;
import com.github.stoynko.prescription_svc.model.Prescription;
import com.github.stoynko.prescription_svc.model.PrescriptionMedicament;
import com.github.stoynko.prescription_svc.property.MedicamentProperties;
import com.github.stoynko.prescription_svc.web.dto.request.AddMedicamentRequest;
import com.github.stoynko.prescription_svc.web.dto.response.MedicamentItemResponse;
import com.github.stoynko.prescription_svc.web.dto.response.PrescriptionMedicamentResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicamentMapper {

    public static Medicament toMedicamentEntity(MedicamentProperties.MedicamentDetails medicamentDetails) {
        return Medicament.builder()
                .medicamentType(medicamentDetails.getType())
                .description(medicamentDetails.getDescription())
                .brandName(medicamentDetails.getBrandName())
                .genericName(medicamentDetails.getGenericName())
                .deliveryMethods(medicamentDetails.getDeliveryMethods())
                .build();
    }

    public static PrescriptionMedicamentResponse toPrescriptionMedicamentResponse(PrescriptionMedicament prescriptionMedicament) {

        Medicament medicament = prescriptionMedicament.getMedicament();

        return PrescriptionMedicamentResponse.builder()
                .id(prescriptionMedicament.getId())
                .medicamentType(medicament.getMedicamentType())
                .description(medicament.getDescription())
                .brandName(medicament.getBrandName())
                .genericName(medicament.getGenericName() == null ? "" : medicament.getGenericName())
                .intakeDosage(prescriptionMedicament.getIntakeDosage())
                .intakeFrequency(prescriptionMedicament.getIntakeFrequency())
                .deliveryMethod(prescriptionMedicament.getDeliveryMethod())
                .treatmentDuration(prescriptionMedicament.getTreatmentDuration())
                .additionalNotes(prescriptionMedicament.getAdditionalNotes())
                .build();
    }

    public static MedicamentItemResponse toMedicamentItemResponse(Medicament medicament) {
        return MedicamentItemResponse.builder()
                .medicamentId(medicament.getId())
                .type(medicament.getMedicamentType())
                .description(medicament.getDescription())
                .brandName(medicament.getBrandName())
                .genericName(medicament.getGenericName())
                .deliveryMethods(medicament.getDeliveryMethods())
                .build();
    }

    public static PrescriptionMedicament toPrescriptionMedicamentEntity(Prescription prescription, Medicament medicament, AddMedicamentRequest request) {
        return PrescriptionMedicament.builder()
                .prescription(prescription)
                .medicament(medicament)
                .intakeDosage(request.getIntakeDosage())
                .intakeFrequency(request.getIntakeFrequency())
                .deliveryMethod(request.getDeliveryMethod())
                .treatmentDuration(request.getTreatmentDuration())
                .additionalNotes(request.getAdditionalNotes())
                .build();
    }
}
