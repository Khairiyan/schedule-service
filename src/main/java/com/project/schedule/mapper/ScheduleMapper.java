package com.project.schedule.mapper;

import com.project.schedule.dto.request.KeberangkatanPostRequest;
import com.project.schedule.model.Keberangkatan;
import com.project.schedule.model.Pesawat;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduleMapper {

    public Keberangkatan mapToModel(KeberangkatanPostRequest request, Pesawat pesawat){
        Keberangkatan keberangkatan = new Keberangkatan();
        UUID scheduleId = UUID.randomUUID();
        keberangkatan.setUuid(scheduleId);
        keberangkatan.setAsal(request.getAsal());
        keberangkatan.setTujuan(request.getTujuan());
        keberangkatan.setTanggal(request.getTanggal());
        keberangkatan.setJadwalKeberangkatan(request.getKeberangkatan());
        keberangkatan.setJadwalKedatangan(request.getKedatangan());
        keberangkatan.setPesawat(pesawat);
        return keberangkatan;
    }
}
