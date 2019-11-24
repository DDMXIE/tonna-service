package com.tony.tonna.service;

import com.tony.tonna.entity.User;
import com.tony.tonna.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(userName);
        if(user == null){
            throw new UsernameNotFoundException("您的账户不存在！！！");
        }
        user.setROLES(userMapper.getUserRolesByUid(user.getUSER_ID()));
        return user;
    }
}
