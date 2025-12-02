package com.github.stoynko.prescription_svc.web.dto.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class CreatePrescriptionRequest {

    private UUID appointmentId;

}
