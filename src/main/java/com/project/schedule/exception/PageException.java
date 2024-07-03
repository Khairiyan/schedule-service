package com.project.schedule.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class PageException extends IllegalArgumentException{

    @Autowired
    public PageException(String message){
        super(message);
    }

}
