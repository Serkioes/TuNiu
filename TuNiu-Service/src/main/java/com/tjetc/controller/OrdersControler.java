package com.tjetc.controller;

import com.tjetc.domain.Orders;
import com.tjetc.domain.TrainTickets;
import com.tjetc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersControler {
    @Autowired
    OrderService orderService;

    @RequestMapping("/list")
    public List<Orders> list(@RequestParam(name = "userId",required = false)Integer userId,
                             @RequestParam(name = "status",defaultValue = "")String  status){
        return orderService.list(userId,status);
    }
    @RequestMapping("/del")
    public int deleteById(Integer id){
        return orderService.deleteById(id);
    }

    @RequestMapping("/save")
    public String save(@RequestParam(name = "userId") Integer userId, TrainTickets trainTickets,
                       @RequestParam(name = "passengers")String passengers,
                       @RequestParam(name = "price")Integer price){
        return orderService.save(userId,trainTickets,passengers,price);
    }

}
