package com.tjetc.controller;

import com.tjetc.domain.City;
import com.tjetc.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping("/list")
    public List<City> list(){
        return cityService.list();
    }

    @RequestMapping("/which")
    public City selectByName(String name){
        return cityService.selectByName(name);
    }
}
