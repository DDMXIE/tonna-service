package com.tony.tonna.service;

import com.tony.tonna.dao.TestUserRepository;
import com.tony.tonna.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestUserService {
    @Autowired
    private TestUserRepository testUserRepository;

    public void saveUser(User user){

        testUserRepository.save(user);//实现数据更新
    }
}
