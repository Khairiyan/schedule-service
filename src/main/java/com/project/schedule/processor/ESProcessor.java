package com.project.schedule.processor;

import com.project.schedule.constant.GeneralConstant;
import com.project.schedule.dto.PesawatDto;
import com.project.schedule.mapper.PesawatListMapper;
import com.project.schedule.mapper.ScheduleMapper;
import com.project.schedule.mapper.esmapper.ScheduleESMapper;
import com.project.schedule.model.Keberangkatan;
import com.project.schedule.model.Pesawat;
import com.project.schedule.model.elasticsearch.KeberangkatanElasticSearch;
import com.project.schedule.repository.elasticsearch.KeberangkatanESRepository;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ESProcessor {

    private final PesawatListMapper pesawatListMapper;

    private final ScheduleESMapper esMapper;

    private final KeberangkatanESRepository keberangkatanESRepository;

    public String saveES(Keberangkatan keberangkatan, Pesawat pesawat){
        PesawatDto pesawatDto = pesawatListMapper.mapperPesawat(pesawat);
        KeberangkatanElasticSearch keberangkatanES = esMapper.mapToModelES(keberangkatan, pesawatDto);
//        keberangkatanESRepository
//        try {
//        } catch (ElasticsearchException exception){
//            log.info("Error : {}", Arrays.toString(exception.getStackTrace()));
//            throw new ElasticsearchException("/api/v1/schedule", exception.response());
//        }
        return GeneralConstant.MessageFromService.SAVED_ES;
    }
}
