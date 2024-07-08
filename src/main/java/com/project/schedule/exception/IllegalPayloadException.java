package com.project.schedule.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class IllegalPayloadException extends IllegalArgumentException{

    @Autowired
    public IllegalPayloadException(String message){
        super(message);
    }
}
