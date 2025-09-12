package com.tjetc.domain;

import lombok.Data;

import java.io.Serializable;

@Data
/**
 * 用户实体类，实现Serializable接口以便对象可以被序列化
 * 序列化是指将对象转换为字节流的过程，常用于网络传输或持久化存储
 */
public class User implements Serializable {
    private Integer id;
    private String account;
    private String password;
    private String name;
}
