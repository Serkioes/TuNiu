package com.tjetc.dao;

import com.tjetc.domain.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Orders> list(@Param("userId")Integer userId,@Param("status")String status);
    @Delete("delete from orders where id = #{id}")
    int deleteById(Integer id);

    @Update("update order set status='已支付' where id =#{tradeNo}")
    void updateById(String tradeNo);

}
