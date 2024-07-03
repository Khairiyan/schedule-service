package com.project.schedule.controller.exception;

import com.project.schedule.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse<String[]>> handleIllegalArgumentException(IllegalArgumentException exception){
        String[] messages = null;
        if(exception.getMessage().contains("%")){
           messages = exception.getMessage().split("%");
        } else {
            messages = exception.getMessage().split("@");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.<String[]>builder().error(messages).build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse<List<String>>> handleDataNotFoundException(RuntimeException exception){
        List<String> messages = Arrays.asList(exception.getMessage().split("@"));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.<List<String>>builder().error(messages).build());
   }
}
