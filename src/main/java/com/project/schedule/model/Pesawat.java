package com.project.schedule.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "pesawat")
@Data
public class Pesawat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    private String maskapai;

    @Column
    private String tipePesawat;

    @OneToOne(mappedBy = "pesawat", cascade = CascadeType.ALL)
    private Keberangkatan keberangkatan;

}
