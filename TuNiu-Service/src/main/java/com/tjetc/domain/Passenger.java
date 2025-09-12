package com.tjetc.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Passenger implements  Serializable {
        private Integer id;
        private String name;
        private String type;
        private String idCard;
        private String phone;
        private Integer userId;
        private String cardType;
        private String country;
        private String gender;
        private String often;
}
