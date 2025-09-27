package com.tjetc.service;

import com.tjetc.domain.TrainTickets;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainTicketsService {
    List<TrainTickets> list(Integer startStation, Integer endStation, String startTime, String condition);

    boolean importAll();
}
