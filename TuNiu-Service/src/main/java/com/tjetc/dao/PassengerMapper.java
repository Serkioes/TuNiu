package com.tjetc.dao;

import com.tjetc.domain.Passenger;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PassengerMapper {

    @Select("select * from passenger where user id=#{id} and name like concat('%',#{name},'%')")
    List<Passenger> list(@Param("userId")Integer userId,@Param("name")String name);

    @Delete("delete from passenger where id=#{id}")
    int deleteById(Integer id);

    @Insert("insert into passenger(name,type,idCard,phone,userId,cardType,country,gender,often)" +
            "values(#{name},#{type},#{idCard},#{phone},#{userId},#{cardType},#{country},#{gender},#{often})")
    int insert(Passenger passenger);

    @Update("update passenger set " +
            "name=#{name},type=#{type},idCard=#{idCard},phone=#{phone},userId=#{userId}," +
            "cardType=#{cardType},country=#{country},gender=#{gender},often=#{often} where id=#{id}")

    int update(Passenger passenger);
}
