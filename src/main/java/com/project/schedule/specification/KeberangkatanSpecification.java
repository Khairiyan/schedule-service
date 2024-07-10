package com.project.schedule.specification;

import com.project.schedule.model.Keberangkatan;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class KeberangkatanSpecification {

    private KeberangkatanSpecification() throws IllegalAccessException{
        throw new IllegalAccessException("utility class");
    }

    public static Specification<Keberangkatan> getByUUID(UUID scheduleId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("uuid"), scheduleId);
    }

    public static Specification<Keberangkatan> hasJadwalKeberangkatan(String jadwalKeberangkatan){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("jadwalKeberangkatan"), jadwalKeberangkatan);
    }

    public static Specification<Keberangkatan> hasAsal(String asal){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("asal"), asal);
    }

    public static Specification<Keberangkatan> hasTanggal(String tanggal){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tanggal"), tanggal);
    }
}
