package com.tjetc.dao;

import com.tjetc.domain.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    @Insert("insert into order_detail(order id,ticket type,ticket id," +
            "passenger id,buy num) " +
            "values(#{orderId},#{ticketType},#{ticketId},#{passengerId},#{buyNum})")
    void add(OrderDetail orderDetail);
}
