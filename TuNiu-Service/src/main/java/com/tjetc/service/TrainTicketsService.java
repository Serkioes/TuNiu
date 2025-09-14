package com.tjetc.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainTicketsService {
    List<TrainTicketsService> list(String startStation, String endStation, String startTime, String condition);

    boolean importAll();
}
