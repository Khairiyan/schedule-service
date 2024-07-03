package com.project.schedule.service.impl;

import com.project.schedule.constant.GeneralConstant;
import com.project.schedule.dto.PesawatDto;
import com.project.schedule.dto.request.PesawatPostRequest;
import com.project.schedule.dto.request.PesawatUpdateRequest;
import com.project.schedule.dto.response.ListApiResponse;
import com.project.schedule.dto.response.Metadata;
import com.project.schedule.dto.response.PesawatPostResponse;
import com.project.schedule.exception.DataNotFoundException;
import com.project.schedule.mapper.PesawatListMapper;
import com.project.schedule.model.Pesawat;
import com.project.schedule.repository.PesawatRepository;
import com.project.schedule.service.PesawatService;
import com.project.schedule.specification.PesawatSpecification;
import com.project.schedule.validation.PageValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PesawatServiceImpl implements PesawatService {

    private final PesawatRepository pesawatRepository;

    private final PesawatListMapper pesawatListMapper;

    private final PageValidation pageValidation;

    @Override
    @Transactional
    public PesawatPostResponse createPesawat(PesawatPostRequest request) {
        Pesawat pesawat = new Pesawat();
        UUID uuid = UUID.randomUUID();
        pesawat.setUuid(uuid);
        pesawat.setTipe_pesawat(request.getTipePesawat());
        pesawat.setMaskapai(request.getMaskapai());
        pesawatRepository.save(pesawat);
        return PesawatPostResponse
                .builder()
                .uuid(pesawat.getUuid().toString())
                .build();
    }

    @Override
    public ListApiResponse<PesawatDto, Metadata> getListPesawat(int pageNumber, int pageSize) {
        pageValidation.validate(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Pesawat> pesawatList = pesawatRepository.findAll(pageable);

        Metadata metadata = new Metadata();
        metadata.setPageMessage(pageNumber + 1, pesawatList.getTotalPages());
        metadata.setTotalData(Integer.toString(pesawatList.get().toList().size()));

        return ListApiResponse.<PesawatDto, Metadata>builder()
                        .status(HttpStatus.OK)
                        .message(GeneralConstant.GeneralMessageSuccessApi.SUCCESS_GET_DATA)
                        .metadata(metadata)
                        .data(pesawatListMapper.mapperListPesawat(pesawatList))
                        .build();
    }

    @Override
    public PesawatDto getPesawat(String pesawatId) {
        Pesawat pesawat = pesawatRepository.findOne(PesawatSpecification.getByUUID(UUID.fromString(pesawatId)))
                .orElseThrow(() -> new DataNotFoundException(String.format(GeneralConstant.ErrorMessageApi.DATA_NOT_FOUND, pesawatId)));
        return pesawatListMapper.mapperPesawat(pesawat);
    }

    @Override
    public PesawatDto updatePesawat(String pesawatId, PesawatUpdateRequest request) {
        Pesawat pesawat = pesawatRepository.findOne(PesawatSpecification.getByUUID(UUID.fromString(pesawatId)))
                .orElseThrow(() -> new DataNotFoundException(String.format(GeneralConstant.ErrorMessageApi.DATA_NOT_FOUND, pesawatId)));
        Pesawat afterMapper = pesawatListMapper.mapperUpdatePesawat(pesawat, request);
        pesawatRepository.save(afterMapper);
        return pesawatListMapper.mapperPesawat(afterMapper);
    }
}
