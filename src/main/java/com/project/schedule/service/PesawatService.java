package com.project.schedule.service;

import com.project.schedule.dto.PesawatDto;
import com.project.schedule.dto.request.PesawatPostRequest;
import com.project.schedule.dto.request.PesawatUpdateRequest;
import com.project.schedule.dto.response.ListApiResponse;
import com.project.schedule.dto.response.Metadata;
import com.project.schedule.dto.response.PesawatPostResponse;



public interface PesawatService {

    public PesawatPostResponse createPesawat(PesawatPostRequest request);

    public ListApiResponse<PesawatDto, Metadata> getListPesawat(int pageNumber, int pageSize, String search);

    public PesawatDto getPesawat(String pesawatId);

    public PesawatDto updatePesawat(String pesawatId, PesawatUpdateRequest request);

    public String delete(String pesawatId);
}
