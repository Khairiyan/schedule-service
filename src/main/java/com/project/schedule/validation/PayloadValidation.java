package com.project.schedule.validation;

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

    private final PesawatRepository pesawatRepository;

        public void validatePostRequestPlane(PesawatPostRequest request, boolean existingPesawat) {
            List<String> errors =  new ArrayList<>();
            Pattern patternNumbers = Pattern.compile("^\\d+$");
            if(!existingPesawat){
                if (patternNumbers.matcher(request.getMaskapai()).matches()){
                    errors.add(String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_PAYLOAD, GeneralConstant.ConstantVariable.MASKAPAI));
                }
                if (patternNumbers.matcher(request.getTipePesawat()).matches()){
                    errors.add(String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_PAYLOAD, GeneralConstant.ConstantVariable.TIPE_PESAWAT));
                }
            } else {
                errors.add(String
                        .format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_SAME_DATA,
                                GeneralConstant.ConstantVariable.MASKAPAI,
                                GeneralConstant.ConstantVariable.TIPE_PESAWAT));
            }
            if (errors.size() > 1){
                throw new IllegalPayloadException(errors.getFirst() + "%" + errors.getLast());
            }
            if(!errors.isEmpty()){
                throw new IllegalPayloadException(errors.getFirst());
            }
        }

        public void validatePatchRequestPlane(Pesawat pesawat, PesawatUpdateRequest request){
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
                throw new IllegalPayloadException(String.format(
                        GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_FOR_UPDATE,
                        GeneralConstant.ConstantVariable.MASKAPAI,
                        GeneralConstant.ConstantVariable.TIPE_PESAWAT));
            }
        }

        public void validatePostRequestSchedule(boolean checkExistingData){
            if(checkExistingData){
                throw new IllegalPayloadException(
                        String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_SAME_DATE_IN_SCHEDULE, "tanggal", "jadwalKeberangkatan", "asal")
                );
            }
        }

}
