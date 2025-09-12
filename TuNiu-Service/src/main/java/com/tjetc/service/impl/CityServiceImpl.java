package com.tjetc.service.impl;

import com.tjetc.dao.CityMapper;
import com.tjetc.domain.City;
import com.tjetc.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper CityMapper;

    @Override
    public List<City> list() {
        return List.of();
    }

    @Override
    public City findByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public City findByName(String name) {
        return null;
    }
}
