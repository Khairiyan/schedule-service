package com.project.schedule.dto.request;

import com.project.schedule.dto.PesawatDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeberangkatanPostRequest {

    private PesawatDto pesawatDto;

    private String tanggal;

    private String keberangkatan;

    private String kedatangan;

    private String asal;

    private String tujuan;
}
