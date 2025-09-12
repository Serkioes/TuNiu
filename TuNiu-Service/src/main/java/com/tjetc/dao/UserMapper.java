package com.tjetc.dao;

import com.tjetc.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where account=#{account}")
    User findByAccount(String account);
    @Select("select * from user where name=#{name}")
    User findByName(String name);
    @Insert("insert into user(account,password,name) values(#{account},#{password},#{name})")
    void register(User user);
    @Select("select account from user where id=#{id}")
    String getAccount(Integer id);
}
