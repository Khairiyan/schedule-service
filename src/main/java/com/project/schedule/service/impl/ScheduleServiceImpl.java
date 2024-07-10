package com.project.schedule.service.impl;

import com.project.schedule.constant.GeneralConstant;
import com.project.schedule.dto.request.KeberangkatanPostRequest;
import com.project.schedule.dto.response.KeberangkatanPostResponse;
import com.project.schedule.exception.DataNotFoundException;
import com.project.schedule.mapper.ScheduleMapper;
import com.project.schedule.mapper.esmapper.ScheduleESMapper;
import com.project.schedule.model.Keberangkatan;
import com.project.schedule.model.Pesawat;
import com.project.schedule.model.elasticsearch.KeberangkatanElasticSearch;
import com.project.schedule.repository.KeberangkatanRepository;
import com.project.schedule.repository.PesawatRepository;
import com.project.schedule.repository.elasticsearch.KeberangkatanESRepository;
import com.project.schedule.service.ScheduleService;
import com.project.schedule.specification.KeberangkatanSpecification;
import com.project.schedule.specification.PesawatSpecification;
import com.project.schedule.validation.PayloadValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final KeberangkatanRepository keberangkatanRepository;

    private final KeberangkatanESRepository keberangkatanESRepository;

    private final PesawatRepository pesawatRepository;

    private final ScheduleMapper scheduleMapper;

    private final PayloadValidation payloadValidation;

    private final ScheduleESMapper scheduleESMapper;

    @Override
    @Transactional
    public KeberangkatanPostResponse createSchedule(KeberangkatanPostRequest request) {
        boolean checkKeberangkatan = keberangkatanRepository
                .exists(KeberangkatanSpecification.getByUUID(UUID.fromString(request.getPesawatDto().getId())));
        payloadValidation.validatePostRequestSchedule(checkKeberangkatan);
        UUID pesawatId = UUID.fromString(request.getPesawatDto().getId());
        Pesawat pesawat = pesawatRepository
                .findOne(PesawatSpecification.getByUUID(pesawatId))
                .orElseThrow(() -> new DataNotFoundException(String.format(GeneralConstant.ErrorMessageApi.DATA_NOT_FOUND, pesawatId.toString())));
        Keberangkatan keberangkatan = scheduleMapper.mapToModel(request, pesawat);
        Keberangkatan keberangkatansave = keberangkatanRepository.save(keberangkatan);
        KeberangkatanElasticSearch keberangkatanElasticSearch = scheduleESMapper.mapToModelES(keberangkatansave, request.getPesawatDto());
        keberangkatanESRepository.save(keberangkatanElasticSearch);
        return KeberangkatanPostResponse.builder().id(keberangkatanElasticSearch.getUuid().toString()).build();
    }
}
