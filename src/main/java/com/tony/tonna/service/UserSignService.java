package com.tony.tonna.service;

import com.tony.tonna.entity.Role;
import com.tony.tonna.entity.User;
import com.tony.tonna.mapper.UserMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserSignService {
    @Resource
    private UserMapper userMapper;

    public int saveSignUser(User user){
        return userMapper.saveSignUser(user);
    }

    public int saveUserRole(String userRoleId,String uid,String rid){
        return userMapper.saveUserRole(userRoleId,uid,rid);
    }

    public List<Role> showAllRoleInfo(){

        return userMapper.showAllRoleInfo();
    }

    /**
     * 方法安全 样例
     * @return String
     */
    @Secured("ROLE_USER")
    public String sayHello(){
        return "Hello Tony, your role is USER!";
    }
}
