package com.tjetc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.core.annotation.Order;
@Mapper
public interface OrderMapper {
    @Insert("insert into order(user,id,time,status,total,price) values(#{userId},#{time},#{status},#{totalPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int add(Order order);
}
