package com.tjetc.controller;

import com.tjetc.domain.TrainTickets;
import com.tjetc.service.TrainTicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trainTickets")
public class TrainTicketsController {
    @Autowired
    TrainTicketsService trainTicketsService;
    @RequestMapping("/list")
    public List<TrainTickets> list(
            @RequestParam(name = "startStation") Integer startStation,
            @RequestParam(name = "endStation") Integer endStation,
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "condition") String condition){
        return trainTicketsService.list(startStation,endStation,startTime,condition);
    }
    @RequestMapping("/importAll")
    public boolean importAll(){
        return trainTicketsService.importAll();
    }

}
