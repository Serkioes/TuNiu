package com.tjetc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface TrainTicketsMapper {
    @Update("update train_tickets set available seats=available seats-1 where id=#{trainTicketsId}")
    void decrementAvailableSeats(Integer id);
}
