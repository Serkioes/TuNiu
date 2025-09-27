package com.tjetc.service.impl;

import com.tjetc.dao.CityMapper;
import com.tjetc.domain.City;
import com.tjetc.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper CityMapper;

    @Override
    public List<City> list() {
        return CityMapper.list();
    }

    @Override
    public City selectByPrimaryKey(Integer id) {
        return CityMapper.selectByPrimaryKey(id);
    }

    @Override
    public City selectByName(String name) {
        return CityMapper.selectByName( name);
    }
}
