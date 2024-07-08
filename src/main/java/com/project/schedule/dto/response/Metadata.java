package com.project.schedule.dto.response;

import com.project.schedule.constant.GeneralConstant;
import com.project.schedule.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Metadata {

    private String page;

    private String totalData;

    public void setPageMessage(int page, int totalPages, String search){
        if(page > totalPages){
            if(!search.isEmpty()){
                throw new DataNotFoundException(String.format(GeneralConstant.ErrorMessageApi.DATA_NOT_FOUND_IN_SEARCH, search));
            }
            throw new DataNotFoundException(String.format(GeneralConstant.ErrorMessageApi.DATA_NOT_FOUND_IN_PAGE, page));
        }
        this.page = String.format("%d of %d", page, totalPages);
    }
}
