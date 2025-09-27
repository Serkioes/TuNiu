package com.tjetc.service.impl;

import com.tjetc.dao.OrderDetailsMapper;
import com.tjetc.dao.OrderMapper;
import com.tjetc.domain.Orders;
import com.tjetc.domain.TrainTickets;
import com.tjetc.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDetailsMapper orderDetailsMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public List<Orders> list(Integer userId, String status) {
        return orderMapper.list(userId,status);
    }


    @Override
    public int deleteById(Integer id){
        orderMapper.deleteById(id);
        orderDetailsMapper.deleteById( id);
        return 1;
    }

    @Override
    public String save(Integer userId, TrainTickets trainTickets, String passengers, Integer price){
        Long I = redisTemplate.opsForValue().decrement("train:ticket"+trainTickets.getId());
        if(I>=0){
            HashMap<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("trainTickets",trainTickets);
            map.put("passengers",passengers);
            map.put("price",price);

            rabbitTemplate.convertAndSend("stockQueue",trainTickets.getId());  //发送消息给库存服务,对mysql按照车票id进行减库存，不是对redis!!!
            rabbitTemplate.convertAndSend("orderQueue",map);  //发送消息给订单服务,对mysql进行订单和订单详情保存，需要的字段有用户id，总价(前端计算(),车票id，乘客id
            return "成功";
        }
        return "失败";
    }
    @Override
    public void updateById(String tradeNo) {
        orderMapper.updateById(tradeNo);
    }
}
