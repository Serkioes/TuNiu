package com.tjetc.dao;

import com.tjetc.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from `user` where account=#{account}")
    User findByUseraccount(String account);
    @Insert("insert into `user`(account,password,name) values(#{account},#{password},#{name})")
    int register(User user);
    @Select("select account from `user` where id=#{id}")
    String getAccount(Integer id);
}
