package com.tjetc.service;

import com.tjetc.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByAccount(String account);

    User findByName(String name);

    String register(User user);

    String getAccount(Integer id);

}
