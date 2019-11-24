package com.tony.tonna.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tony.tonna.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        System.out.println("TokenInterceptor>>>preHandle");

        User user = new User();
        String token = null;
        try{
            String bearerToken = request.getHeader("Authorization");
            token = bearerToken.substring(7,bearerToken.length());
            System.out.println("------------这里是token---------"+token);
            /**该语句需要实例化注入拦截器的 Bean （详见拦截器配置文件：TokenInterceptorConfig文件）
             * 不然会导致 redisTemplate 注入不进来，空指针异常*/
            ValueOperations op = redisTemplate.opsForValue();
            user = (User) op.get(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(request.getHeader("Authorization")!=null && user!=null) {
            System.out.println(request.getHeader("Authorization"));
            return true;
        } else{
            System.out.println("---------被拦截--------");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            response.setStatus(500);
            Map errorInfo = new HashMap();
            errorInfo.put("status",500);
            if(token!=null && user ==null){
                errorInfo.put("msg","Token 已过期，请重新登录验证!");
            }else{
                errorInfo.put("msg","Token 验证失败，请重新登录验证!");
            }
            ObjectMapper om = new ObjectMapper();
            out.write(om.writeValueAsString(errorInfo));
            out.flush();
            out.close();
            return false;
        }

    }
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView){
        System.out.println("TokenInterceptor>>>postHandle");
    }
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex){
        System.out.println("TokenInterceptor>>>afterCompletion");
    }
}
