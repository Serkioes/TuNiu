package com.tjetc.receiver;

import com.tjetc.dao.OrderDetailMapper;
import com.tjetc.dao.OrderMapper;
import com.tjetc.domain.OrderDetail;
import com.tjetc.domain.Orders;
import com.tjetc.domain.TrainTickets;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class OrdersReceiver {
    @Autowired
    private OrderMapper ordersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @RabbitListener(queues = "orderQueue")
    public void receive(Map<String, Object> map){
        Orders order = new Orders();
        order.setStatus("待支付");
        order.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        order.setUerId((Integer) map.get("userId"));
        order.setTotalPrice((String)map.get("price"));
        ordersMapper.add(order);    //订单添加成功

        String passengers = (String) map.get("passengers");   //拿到消息队列里传递过来的乘客，有几个乘客，中间关系表就有几条记录

        passengers = passengers.replace("[", "");
        passengers = passengers.replace("]", "");
        String[] split = passengers.split(",");     //将乘客id拆分成数组，以便后续遍历每一个乘客的id，并添加到中间关系表
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId());
        orderDetail.setTicketType("火车票");
        orderDetail.setTicketId(((TrainTickets) map.get("trainTickets")).getId());
        orderDetail.setBuyNum(1);

        for (String s : split) {
            orderDetail.setPassengerId(Integer.parseInt(s));
            orderDetailMapper.add(orderDetail);
        }

    }
}
