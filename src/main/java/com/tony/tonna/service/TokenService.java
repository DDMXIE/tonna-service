package com.tony.tonna.service;

import com.tony.tonna.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * token拦截 并获取用户信息
     * @param request
     * @return
     */
    public Map showUserInfoByToken(HttpServletRequest request){
        Map outputdata = new HashMap();
        String bearerToken = request.getHeader("Authorization");
        System.out.println("bearer token"+bearerToken);
        String token = bearerToken.substring(7,bearerToken.length());
        System.out.println("token"+token);
        User user = new User();
        try{
            ValueOperations op = redisTemplate.opsForValue();
            user = (User) op.get(token);
            outputdata.put("status",200);
            outputdata.put("msg","成功！");
            outputdata.put("data",user);

        }catch (Exception e){
            e.printStackTrace();
        }
        return outputdata;

    }

    /**
     * token 拦截测试
     * @return
     */
    public Map validateTokenSayHello(){
        Map outputdata = new HashMap();
        outputdata.put("data","sayhello!!!");
        return outputdata;
    }
}
