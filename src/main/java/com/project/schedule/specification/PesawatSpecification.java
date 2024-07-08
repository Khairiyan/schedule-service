package com.project.schedule.specification;

import com.project.schedule.model.Pesawat;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class PesawatSpecification {

    private PesawatSpecification() throws IllegalAccessException {
        throw new IllegalAccessException("Utility Class");
    }

    public static Specification<Pesawat> getByUUID(UUID uuid){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("uuid"), uuid));
    }

    public static Specification<Pesawat> search(String parameter, String search){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(parameter)), "%" + search.toLowerCase() + "%" ));
    }

    public static Specification<Pesawat> hasMaskapaiName(String maskapai){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("maskapai"), maskapai);
    }

    public static Specification<Pesawat> hasTipePesawat(String tipePesawat){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipePesawat"), tipePesawat);
    }

}
