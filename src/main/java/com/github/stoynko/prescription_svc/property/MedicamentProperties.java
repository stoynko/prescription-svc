package com.github.stoynko.prescription_svc.property;

import com.github.stoynko.prescription_svc.config.YamlPropertySourceFactory;
import com.github.stoynko.prescription_svc.model.enums.DosageDelivery;
import com.github.stoynko.prescription_svc.model.enums.MedicamentType;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Set;

@Data
@Configuration
@ConfigurationProperties
@PropertySource(value = "medicaments-catalog.yaml", factory = YamlPropertySourceFactory.class)
public class MedicamentProperties {

    private List<MedicamentDetails> medicaments;

    @Data
    public static class MedicamentDetails {

        private MedicamentType type;

        private String brandName;

        private String genericName;

        private Set<DosageDelivery> deliveryMethods;
    }
}

