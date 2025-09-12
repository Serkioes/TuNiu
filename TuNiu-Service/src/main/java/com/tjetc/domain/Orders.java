package com.tjetc.domain;

import com.alipay.api.domain.Passenger;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Orders implements Serializable {
    private Integer id;
    private Integer userId;
    private String time;
    private String status;
    private Integer totalPrice;
    private TrainTickets trainTickets;
    private List<Passenger> passengers;
}
