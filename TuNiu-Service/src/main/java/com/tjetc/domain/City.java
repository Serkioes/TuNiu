package com.tjetc.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class City implements Serializable {
    private Integer id;
    private String name;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
}
