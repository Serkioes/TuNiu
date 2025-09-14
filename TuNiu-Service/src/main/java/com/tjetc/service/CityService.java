package com.tjetc.service;

import com.tjetc.domain.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    List<City> list();
    City selectByPrimaryKey(Integer id);
    City selectByName(String name);
}
