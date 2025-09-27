package com.tjetc.service.impl;

import com.tjetc.dao.CityMapper;
import com.tjetc.dao.TrainTicketsMapper;
import com.tjetc.domain.TrainTickets;

import com.tjetc.service.TrainTicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainTicketsServiceImpl implements TrainTicketsService {
    @Autowired
    TrainTicketsMapper trainTicketsMapper;
    @Autowired
    CityMapper cityMapper;
    @Autowired
    StringRedisTemplate redisTemplate;

    public boolean importAll(){
        List<TrainTickets> list = trainTicketsMapper.importAll();
        try {
            for(TrainTickets tt:list){
                redisTemplate.opsForValue().set("train:ticket"+tt.getId().toString(),tt.getAvailableSeats().toString());
            }
        } catch (Exception e) {
            System.out.println("Redis 不可用，跳过库存设置: " + e.getMessage());
        }
        return true;
    }
    @Override
    public List<TrainTickets> list(Integer startStation, Integer endStation,String startTime,String condition) {

        String startStationName = cityMapper.selectByPrimaryKey(startStation).getName();
        String endStationName = cityMapper.selectByPrimaryKey(endStation).getName();

        return trainTicketsMapper.list(startStationName,endStationName,startTime,condition);
    }
}
