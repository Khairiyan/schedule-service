package com.project.schedule.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse<T> {

    private T data;

    private HttpStatus status;

    private String message;

    private T metadata;
}
