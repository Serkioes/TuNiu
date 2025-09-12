package com.tjetc.service;

import com.tjetc.domain.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    List<City> list();
    City findByPrimaryKey(Integer id);
    City findByName(String name);
}
