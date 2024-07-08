package com.project.schedule.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.schedule.constant.GeneralConstant;
import com.project.schedule.dto.request.PesawatPostRequest;
import com.project.schedule.exception.IllegalPayloadException;
import com.project.schedule.model.Pesawat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PayloadValidation {

    private final ObjectMapper objectMapper;

        public void validatePostRequest(PesawatPostRequest request, Pesawat pesawat) {
            List<String> errors =  new ArrayList<>();
            Pattern patternNumbers = Pattern.compile("^\\d+$");
            if(pesawat == null){
                if (patternNumbers.matcher(request.getMaskapai()).matches()){
                    errors.add(String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_PAYLOAD, "maskapai"));
                }
                if (patternNumbers.matcher(request.getTipePesawat()).matches()){
                    errors.add(String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_PAYLOAD, "tipePesawat"));
                }
            } else {
                if(pesawat.getTipe_pesawat().equals(request.getTipePesawat()) && pesawat.getMaskapai().equals(request.getMaskapai())){
                    errors.add(String.format(GeneralConstant.ErrorMessageApi.ILLEGAL_INPUT_SAME_DATA, "maskapai", "tipePesawat"));
                }
            }
            if (errors.size() > 1){
                throw new IllegalPayloadException(errors.getFirst() + "%" + errors.getLast());
            }
            if(!errors.isEmpty()){
                throw new IllegalPayloadException(errors.getFirst());
            }
        }

}
