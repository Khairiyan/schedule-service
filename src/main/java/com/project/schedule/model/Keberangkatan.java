package com.project.schedule.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "keberangkatan")
@Data
public class Keberangkatan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private UUID uuid;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pesawatId", referencedColumnName = "uuid")
    private Pesawat pesawat;

    private String tanggal;

    @Column(name = "keberangkatan")
    private String jadwalKeberangkatan;

    @Column(name = "kedatangan")
    private String jadwalKedatangan;

    private String asal;

    private String tujuan;

}
