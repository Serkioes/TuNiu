package com.tjetc.dao;

import com.tjetc.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {
    @Select("select * from city")
    List<City> list();

    @Select("select * from city where id = #{id}")
    City selectByPrimaryKey(Integer id);

    @Select("select * from city where name = #{name}")
    City selectByName(String name);
}
