package com.project.schedule.repository;

import com.project.schedule.model.Pesawat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PesawatRepository extends JpaRepository<Pesawat, Long>, JpaSpecificationExecutor<Pesawat> {
}
