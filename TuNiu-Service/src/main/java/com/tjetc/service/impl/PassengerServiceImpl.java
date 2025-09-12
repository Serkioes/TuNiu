package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.dao.PassengerMapper;
import com.tjetc.domain.Passenger;
import com.tjetc.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;


    @Override
    public int deletleById(Integer id) {
        return passengerMapper.deleteById(id);
    }

    @Override
    public int save(Passenger passenger) {
        if (passenger.getId()==null){
            return passengerMapper.insert(passenger);
        }else {
            return passengerMapper.update(passenger);
        }
    }

    @Override
    public PageInfo<Passenger> list(Integer pageNum, Integer pageSize, Integer userId, String name) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(passengerMapper.list(userId,name));
    }

}
