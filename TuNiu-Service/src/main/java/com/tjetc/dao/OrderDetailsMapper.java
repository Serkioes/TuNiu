package com.tjetc.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailsMapper {
    @Delete("delete from order_detail where order_id=#{id}")
    void deleteById(Integer id);
}
