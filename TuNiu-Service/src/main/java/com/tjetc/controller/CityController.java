package com.tjetc.controller;

import com.tjetc.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")

public class CityController {
    @Autowired
    private CityService cityService;

}
