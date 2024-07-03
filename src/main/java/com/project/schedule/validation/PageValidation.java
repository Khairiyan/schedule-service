package com.project.schedule.validation;

import com.project.schedule.exception.PageException;
import org.springframework.stereotype.Service;
import com.project.schedule.constant.GeneralConstant;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageValidation {

    public void validate(int pageNumber, int pageSize){
        List<String> errors = new ArrayList<>();
        if(pageSize <= 0 ){
            errors.add(GeneralConstant.ErrorMessageApi.ILLEGAL_ARGUMENT_SIZE_PAGE);
        }
        if(pageNumber + 1 <= 0){
            errors.add(GeneralConstant.ErrorMessageApi.ILLEGAL_ARGUMENT_PAGE_NUMBER);
        }
        if(errors.size() > 1){
            throw new PageException(errors.get(0) + "%" + errors.get(1));
        } else if(errors.size() == 1){
            throw new PageException(errors.getFirst());
        }
    }
}
