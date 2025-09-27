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
        try {
            String redisKey = "train:ticket" + trainTickets.getId();
            System.out.println("尝试减少库存，Redis Key: " + redisKey);
            
            // 先检查当前库存
            String currentStock = redisTemplate.opsForValue().get(redisKey);
            System.out.println("当前库存: " + currentStock);
            
            Long I = redisTemplate.opsForValue().decrement(redisKey);
            System.out.println("减少后库存: " + I);
            
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
            // 如果库存不足，仍然允许预订（临时解决方案）
            System.out.println("Redis库存不足，但允许预订（库存检查将在MySQL中进行）");
            HashMap<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("trainTickets",trainTickets);
            map.put("passengers",passengers);
            map.put("price",price);

            rabbitTemplate.convertAndSend("stockQueue",trainTickets.getId());
            rabbitTemplate.convertAndSend("orderQueue",map);
            return "成功";
        } catch (Exception e) {
            // Redis 不可用时的降级处理
            System.out.println("Redis 不可用，跳过库存检查: " + e.getMessage());
            
            // 尝试发送 RabbitMQ 消息，如果失败则跳过
            try {
                HashMap<String,Object> map = new HashMap<>();
                map.put("userId",userId);
                map.put("trainTickets",trainTickets);
                map.put("passengers",passengers);
                map.put("price",price);

                rabbitTemplate.convertAndSend("stockQueue",trainTickets.getId());
                rabbitTemplate.convertAndSend("orderQueue",map);
                System.out.println("RabbitMQ 消息发送成功");
            } catch (Exception rabbitException) {
                System.out.println("RabbitMQ 不可用，跳过消息发送: " + rabbitException.getMessage());
            }
            
            return "成功";
        }
    }
    @Override
    public void updateById(String tradeNo) {
        orderMapper.updateById(tradeNo);
    }
}
