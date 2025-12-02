package com.github.stoynko.prescription_svc.service;

import com.github.stoynko.prescription_svc.exception.MedicamentDoesNotExistException;
import com.github.stoynko.prescription_svc.model.Medicament;
import com.github.stoynko.prescription_svc.repository.MedicamentRepository;
import com.github.stoynko.prescription_svc.web.dto.mapper.MedicamentMapper;
import com.github.stoynko.prescription_svc.web.dto.response.MedicamentItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicamentService {

    private final MedicamentRepository repository;

    public List<MedicamentItemResponse> getAllMedicaments() {
        return repository.findAll().stream()
                         .map(MedicamentMapper::toMedicamentItemResponse)
                         .collect(Collectors.toList());
    }

    public Medicament getMedicamentById(UUID medicamentId) {
        return repository.findById(medicamentId).orElseThrow(() -> new MedicamentDoesNotExistException());
    }
}
