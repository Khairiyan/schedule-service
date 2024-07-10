package com.project.schedule.mapper.esmapper;

import com.project.schedule.dto.PesawatDto;
import com.project.schedule.model.Keberangkatan;
import com.project.schedule.model.elasticsearch.KeberangkatanElasticSearch;
import org.springframework.stereotype.Service;

@Service
public class ScheduleESMapper {

    public KeberangkatanElasticSearch mapToModelES(Keberangkatan keberangkatan, PesawatDto pesawatDto){
        KeberangkatanElasticSearch response =  new KeberangkatanElasticSearch();
        response.setId(keberangkatan.getId());
        response.setTanggal(keberangkatan.getTanggal());
        response.setUuid(keberangkatan.getUuid());
        response.setAsal(keberangkatan.getAsal());
        response.setTujuan(keberangkatan.getTujuan());
        response.setJadwalKeberangkatan(keberangkatan.getJadwalKeberangkatan());
        response.setJadwalKedatangan(keberangkatan.getJadwalKedatangan());
        response.setPesawat(pesawatDto);
        return response;
    }
}
