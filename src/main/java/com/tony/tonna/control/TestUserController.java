/**
 * Created by Xiem
 * Time: 2019/8/17 9:38
 */
package com.tony.tonna.control;



import com.tony.tonna.dao.TestUserRepository;
import com.tony.tonna.entity.User;
import com.tony.tonna.service.TestUserService;
import com.tony.tonna.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class TestUserController {

    @Resource
    private UuidUtil uuidUtil;

    @Autowired
    private TestUserService testUserService;


    @RequestMapping("/data")
    public User test(){
        User user = new User();
        user.setUId(uuidUtil.createUUid());
        user.setAge(12);
        user.setName("phpfzh");
        testUserService.saveUser(user);//实现数据更新
        return user;
    }

}
