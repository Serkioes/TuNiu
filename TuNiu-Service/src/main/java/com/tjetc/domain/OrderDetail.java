package com.tjetc.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class OrderDetail implements Serializable {
    private Integer id;
    private Integer orderId;
    private String ticketType;
    private Integer ticketId;
    private Integer passengerId;
    private Integer buyNum;
}
