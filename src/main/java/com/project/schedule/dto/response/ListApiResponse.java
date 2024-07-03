package com.project.schedule.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.List;

@Data
@Builder
public class ListApiResponse<T, M> {

    private List<T> data;

    private HttpStatus status;

    private String message;

    private M metadata;
}
