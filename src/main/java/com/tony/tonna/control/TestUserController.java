/**
 * Created by Xiem
 * Time: 2019/8/17 9:38
 */
package com.tony.tonna.control;

import com.tony.tonna.entity.User;
import com.tony.tonna.service.TestUserService;
import com.tony.tonna.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestUserController {

    @Resource
    private UuidUtil uuidUtil;

    @Autowired
    private TestUserService testUserService;


    @RequestMapping("/data")
    public Map test(){
        HashMap outputdata  = new HashMap();
        User user = new User();
        user.setUId(uuidUtil.createUUid());
        user.setAge(12);
        user.setName("phpfzh");
        testUserService.saveUser(user);//实现数据更新
        outputdata.put("USER_INFO",user);
        return outputdata;
    }

    /**
     * Restful GET 获取前端参数
     * @param username
     * @param userage
     */
    @RequestMapping(value="/user",method=RequestMethod.GET)
    public void query(@RequestParam(name="username",required = true) String username,@RequestParam(name="userage",required = true) String userage){
        System.out.println(username);
        System.out.println(userage);
    }

}
