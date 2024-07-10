package com.project.schedule.controller.exception;

import com.project.schedule.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class HandlerExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse<String[]>> handleIllegalArgumentException(IllegalArgumentException exception){
        log.info("Error Exception : {}", exception.getLocalizedMessage());
        String[] messages = null;
        if(exception.getMessage().contains("%")){
           messages = exception.getMessage().split("%");
        } else {
            messages = exception.getMessage().split("@");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.<String[]>builder().error(messages).build());
    }

//    @ExceptionHandler(ElasticsearchException.class)
//    public ResponseEntity<ErrorResponse<List<String>>> handleDataNotFoundException(ElasticsearchException exception){
//        log.info("Error Exception : {}", exception.getLocalizedMessage());
//        List<String> messages = Arrays.asList(exception.getMessage().split("@"));
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.<List<String>>builder().error(messages).build());
//   }

   @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse<List<String>>> handleNullPointerException(NullPointerException exception){
        log.info("Error Exception : {}", exception.getLocalizedMessage());
        List<String> messages = Arrays.asList(exception.getMessage().split(","));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.<List<String>>builder().error(messages).build());
   }
}
