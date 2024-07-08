package com.project.schedule.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.schedule.constant.GeneralConstant;
import com.project.schedule.dto.request.PesawatPostRequest;
import com.project.schedule.dto.request.PesawatUpdateRequest;
import com.project.schedule.exception.IllegalPayloadException;
import com.project.schedule.model.Pesawat;
import com.project.schedule.repository.PesawatRepository;
import com.project.schedule.specification.PesawatSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PayloadValidation {

    private final ObjectMapper objectMapper;

    private final PesawatRepository pesawatRepository;

        public void validatePostRequest(PesawatPostRequest request, boolean existingPesawat) {
            List<String> errors =  new ArrayList<>();
            Pattern patternNumbers = Pattern.compile("^\\d+$");
            if(!existingPesawat){
                if (patternNumbers.matcher(request.getMaskapai()).matches()){
                    errors.add(String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_PAYLOAD, "maskapai"));
                }
                if (patternNumbers.matcher(request.getTipePesawat()).matches()){
                    errors.add(String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_PAYLOAD, "tipePesawat"));
                }
            } else {
                errors.add(String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_SAME_DATA, "maskapai", "tipePesawat"));
            }
            if (errors.size() > 1){
                throw new IllegalPayloadException(errors.getFirst() + "%" + errors.getLast());
            }
            if(!errors.isEmpty()){
                throw new IllegalPayloadException(errors.getFirst());
            }
        }

        public void validatePatchRequest(Pesawat pesawat, PesawatUpdateRequest request){
            boolean checkBoolean = false;
            if(request.getTipePesawat() != null && request.getMaskapai() != null){
                checkBoolean = pesawatRepository
                        .exists(
                                PesawatSpecification
                                        .hasTipePesawat(request.getTipePesawat())
                                        .and(PesawatSpecification.hasMaskapaiName(request.getMaskapai()))
                        );
            } else if(request.getTipePesawat() == null && request.getMaskapai() != null){
                checkBoolean = pesawatRepository
                        .exists(
                                PesawatSpecification
                                        .hasTipePesawat(pesawat.getTipePesawat())
                                        .and(PesawatSpecification.hasMaskapaiName(request.getMaskapai()))
                        );
            } else if(request.getTipePesawat() != null){
                checkBoolean = pesawatRepository
                        .exists(
                                PesawatSpecification
                                        .hasTipePesawat(request.getTipePesawat())
                                        .and(PesawatSpecification.hasMaskapaiName(pesawat.getMaskapai()))
                        );
            }

            if(checkBoolean){
                throw new IllegalPayloadException(String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_FOR_UPDATE, "maskapai", "tipePesawat"));
            }
        }

}
