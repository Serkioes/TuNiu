package com.tjetc.service;

import com.tjetc.domain.Orders;
import com.tjetc.domain.TrainTickets;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
        List<Orders> list(Integer userId,String status);

        int deleteById(Integer id);

        String save(Integer userId, TrainTickets trainTickets,String passengers,Integer price);

        void updateById(String tradeNo);
}
