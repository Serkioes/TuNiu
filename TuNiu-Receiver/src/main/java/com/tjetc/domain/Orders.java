package com.tjetc.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Orders implements Serializable {
    private Integer id;
    private Integer uerId;
    private String time;
    private String status;
    private String totalPrice;
    private List<Passenger> passengers;
    private  TrainTickets trainTickets;
}
