package com.project.schedule.service;

import com.project.schedule.dto.request.KeberangkatanPostRequest;
import com.project.schedule.dto.response.KeberangkatanPostResponse;

public interface ScheduleService {
    public KeberangkatanPostResponse createSchedule(KeberangkatanPostRequest request);
}
