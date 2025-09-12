package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengerService {
    int deletleById(Integer id);

    int save(Passenger passenger);

    PageInfo<Passenger> list(Integer pageNum, Integer pageSize, Integer userId, String name);
}
