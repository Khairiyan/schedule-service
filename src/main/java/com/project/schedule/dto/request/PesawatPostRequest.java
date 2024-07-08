package com.project.schedule.dto.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class PesawatPostRequest {

    @NonNull
    private String maskapai;

    @NonNull
    private String tipePesawat;
}
