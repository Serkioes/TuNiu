package com.tjetc.dao;

import com.tjetc.domain.TrainTickets;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrainTicketsMapper {
    List<TrainTickets> list(@Param("startStation")String startStation,
                                  @Param("endStation")String endStation,
                                  @Param("startTime")String startTime,
                                  @Param("condition")String condition);

    @Select("select * from train_tickets")
    List<TrainTickets> importAll();

}
