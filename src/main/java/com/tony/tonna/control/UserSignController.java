package com.tony.tonna.control;

import com.tony.tonna.entity.User;
import com.tony.tonna.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class UserSignController {
    @Autowired
    private UserSignService userSignService;

    @PostMapping(value = "/super/saveSignUser")
    public int saveSignUser(@RequestBody Map userInfoList){
        User user = new User();
        String uid = UUID.randomUUID().toString();
        user.setUSER_ID(uid);
        user.setUSER_NAME(userInfoList.get("username").toString());
        //将密码加密存储到数据库
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encoderPassword = encoder.encode(userInfoList.get("password").toString());
        user.setUSER_PASSWORD(encoderPassword);
        user.setUSER_ENABLED(true);
        user.setUSER_LOCKED(false);
        String rid = "56778fvk-rg9f-4dd6-9f03-b1235e883321"; //这是权限为user（用户）的ROLE id
        String userRoleId = UUID.randomUUID().toString();
        userSignService.saveUserRole(userRoleId,uid,rid);
        return userSignService.saveSignUser(user);
    }
}
