package com.tjetc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrainTicketsMapper {
    List<TrainTicketsMapper> list(@Param("startStation")String startStation,
                                  @Param("endStation")String endStation,
                                  @Param("startTime")String startTime,
                                  @Param("condition")String condition);

    @Select("select * from train tickets")
    List<TrainTicketsMapper> importAll();

}
