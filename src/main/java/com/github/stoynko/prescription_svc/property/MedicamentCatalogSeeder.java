package com.github.stoynko.prescription_svc.property;

import com.github.stoynko.prescription_svc.repository.MedicamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.github.stoynko.prescription_svc.web.dto.mapper.EntityMapper.toMedicamentEntity;

@Component
@RequiredArgsConstructor
public class MedicamentCatalogSeeder implements CommandLineRunner {

    private final MedicamentProperties properties;
    private final MedicamentRepository repository;


    @Override
    public void run(String... args) {
        properties.getMedicaments().stream().forEach(medicament -> {
            boolean exists = repository.existsByMedicamentTypeAndBrandName(
                    medicament.getType(), medicament.getBrandName());
            if (!exists) {
                repository.save(toMedicamentEntity(medicament));
            }
        });
    }
}
