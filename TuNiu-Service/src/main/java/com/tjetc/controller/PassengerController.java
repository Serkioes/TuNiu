package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Passenger;
import com.tjetc.service.PassengerService;
import com.tjetc.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @RequestMapping("/list")
    public PageInfo<Passenger> list(
            @RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
            @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize,
            @RequestParam(name = "userId")Integer userId,
            @RequestParam(name = "name",defaultValue = "")String name){
        return passengerService.list(pageNum,pageSize,userId,name);
    }

    @RequestMapping("/del")
    public String delete(Integer id){
        return passengerService.deletleById(id)> 0 ? "删除成功":"删除失败";
    }
    @RequestMapping("/save")
    public Result save(@RequestBody Passenger passenger){
        if (passengerService.save(passenger)> 0) {
            return Result.ok(200,"成功");
        }
        return Result.error(500,"失败");
    }

}
