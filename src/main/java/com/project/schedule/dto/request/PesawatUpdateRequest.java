package com.project.schedule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PesawatUpdateRequest {

    private String tipePesawat;

    private String maskapai;
}
