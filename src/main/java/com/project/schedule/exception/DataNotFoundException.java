package com.project.schedule.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class DataNotFoundException extends NullPointerException {

    @Autowired
    public DataNotFoundException(String message){
        super(message);
    }
}
