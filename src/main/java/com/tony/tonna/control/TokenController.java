package com.tony.tonna.control;

import com.tony.tonna.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @GetMapping("/user/showUserInfoByToken")
    public Map showUserInfoByToken(HttpServletRequest request){
        return tokenService.showUserInfoByToken(request);
    }

    @GetMapping("/admin/validateTokenSayHello")
    public Map validateTokenSayHello(HttpServletRequest request){
        return tokenService.validateTokenSayHello();
    }
}
