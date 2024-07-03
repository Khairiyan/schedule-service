package com.project.schedule.controller;

import com.project.schedule.dto.request.KeberangkatanPostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class KeberangkatanController {

    @PostMapping(
            value = "/schedule"
    )
    public ResponseEntity<String> createKeberangkatan(@RequestBody KeberangkatanPostRequest request){
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
