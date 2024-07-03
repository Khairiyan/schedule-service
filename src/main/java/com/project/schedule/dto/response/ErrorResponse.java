package com.project.schedule.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse<T> {

    private T error;

}
