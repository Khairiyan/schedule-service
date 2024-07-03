package com.project.schedule.specification;

import com.project.schedule.model.Pesawat;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class PesawatSpecification {

    public static Specification<Pesawat> getByUUID(UUID uuid){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("uuid"), uuid));
    }
}
