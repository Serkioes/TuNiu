package com.tjetc.receiver;

import com.tjetc.dao.TrainTicketsMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainTicketsReceiver {
    @Autowired
    private TrainTicketsMapper trainTicketsMapper;

    @RabbitListener(queues = "stockQueue")
    public void receive(String trainTicketsId){
        // 将String类型转换为Integer类型
        Integer id = Integer.valueOf(trainTicketsId);
        trainTicketsMapper.decrementAvailableSeats(id);
    }
}
