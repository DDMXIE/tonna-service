package com.tony.tonna.control;

import com.tony.tonna.entity.Role;
import com.tony.tonna.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserSignService userSignService;

    @GetMapping("/super/hello")
    public String superadmin() {
        return "hello super";
    }
    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }
    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }

    @GetMapping(value = "/loginfailure")
    public String loginfailure(){
        return "failure";
    }

    @GetMapping("/super/showAllUserInfo")
    public List<Role> showAllUserInfo(){
        List<Role> userlist = new ArrayList<>();
        userlist = userSignService.showAllRoleInfo();
        return userlist;
    }

    /**
     * 方法安全 样例
     * @return String
     */
    @GetMapping("/sayHello")
    public String sayHello(){
        return userSignService.sayHello();
    }
}
