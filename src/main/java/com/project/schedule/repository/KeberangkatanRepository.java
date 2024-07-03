package com.project.schedule.repository;

import com.project.schedule.model.Keberangkatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeberangkatanRepository extends JpaRepository<Keberangkatan, Integer> {
}
