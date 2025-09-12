package com.tjetc.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class TrainTickets implements Serializable {
    private Integer id;
    private String trainNumber;//车次
    private String startStation;//出发站
    private String endStation;//到达站
    private String startTime;//出发时间
    private String endTime;//到达时间
    private String seatType;//座位类型
    private String seatNumber;//座位号
    private Double price;//票价
    private Integer availableSeats;//状态
    private String lasts;  //运行时长

}
