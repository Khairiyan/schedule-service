package com.project.schedule.mapper;

import com.project.schedule.dto.PesawatDto;
import com.project.schedule.dto.request.PesawatUpdateRequest;
import com.project.schedule.model.Pesawat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PesawatListMapper {

    public List<PesawatDto> mapperListPesawat(Page<Pesawat> pesawatList){
        List<PesawatDto> responseList = new ArrayList<>();
        for(Pesawat pesawat : pesawatList){
            PesawatDto response = new PesawatDto();
            response.setId(pesawat.getUuid().toString());
            response.setMaskapai(pesawat.getMaskapai());
            response.setTipePesawat(pesawat.getTipe_pesawat());
            responseList.add(response);
        }
        return responseList;
    }

    public PesawatDto mapperPesawat(Pesawat pesawat){
        return PesawatDto.builder()
                .id(pesawat.getUuid().toString())
                .tipePesawat(pesawat.getTipe_pesawat())
                .maskapai(pesawat.getMaskapai())
                .build();
    }

    public Pesawat mapperUpdatePesawat(Pesawat pesawat, PesawatUpdateRequest request){
        if(request.getTipePesawat() != null){
            pesawat.setTipe_pesawat(request.getTipePesawat());
        }
        if(request.getMaskapai() != null){
            pesawat.setMaskapai(request.getMaskapai());
        }
        return pesawat;
    }
}
