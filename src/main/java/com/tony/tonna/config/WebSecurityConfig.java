package com.tony.tonna.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tony.tonna.entity.User;
import com.tony.tonna.mapper.UserMapper;
import com.tony.tonna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;
    @Resource
    UserMapper userMapper;
    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /** 配置权限的继承关系 */
    @Bean(name = "roleHierarchy")
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_SUPER > ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                /**     配置 动态配置权限的类  */
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(cfisms());
                        object.setAccessDecisionManager(cadm());
                        return object;
                    }
                })
//                //权限配置
//                .antMatchers("/super/**").hasRole("SUPER")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                //放行不需权限的接口
//                .antMatchers("/saveSignUser").permitAll()
//                .antMatchers("/showUserInfoByToken").permitAll()
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureForwardUrl("/loginfailure")
                .loginProcessingUrl("/login").permitAll()
                //自定义参数
                .usernameParameter("username")
                .passwordParameter("password")
                //登录成功 返回成功JSON
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        Object principal = auth.getPrincipal();
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(200);
                        Map<String,Object> map = new HashMap<>();
                        map.put("status",200);
//                        map.put("msg",principal);
                        map.put("msg","登录成功！");
                        User userprincipal = (User) principal;
                        //---------------------- 开始对token进行处理 ----------------------
                        String userId = userprincipal.getUSER_ID();
                        String userOldToken = userMapper.getTokenByUserId(userId);
                        if(userOldToken!= null){
                            redisTemplate.delete(userOldToken);
                        }
                        String username = userprincipal.getUSER_NAME();
                        map.put("username",username);
                        /** token 采用UUID后 强哈希加密 保证安全性*/
                        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
                        String encoderUUID = encoder.encode(UUID.randomUUID().toString());
                        String token = encoderUUID;
//                        String token = UUID.randomUUID().toString();
                        map.put("token",token);
                        userMapper.deleteUserTokenByUid(userId);
                        userMapper.saveUserToken(UUID.randomUUID().toString(),userId,token);
                        //redis
                        try{
                            ValueOperations ops1 = redisTemplate.opsForValue();
                            //设置redis的key过期时间
//                            ops1.set(token,principal,60, TimeUnit.SECONDS);
                            ops1.set(token,principal,10, TimeUnit.MINUTES);
                            User userInfo = (User) ops1.get(token);
                            System.out.println(userInfo.getUSER_NAME()+" 已登录！");
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();

                    }
                })
                //登录失败 根据异常返回失败JSON
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(401);
                        Map<String,Object> map = new HashMap<>();
                        map.put("status",401);
                        if(e instanceof LockedException){
                            map.put("msg","账户被锁定，登录失败！");
                        }else if(e instanceof BadCredentialsException){
                            map.put("msg","账户或密码输入错误，登录失败！");
                        }else if(e instanceof DisabledException){
                            map.put("msg","账户被禁用，登录失败");
                        }else if(e instanceof AccountExpiredException){
                            map.put("msg","账户已过期，登录失败");
                        }else if(e instanceof CredentialsExpiredException){
                            map.put("msg","密码已过期，登录失败");
                        }else{
                            map.put("msg","登录失败");
                        }
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();

                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) {
                        User user = (User) authentication.getPrincipal();
                        String userId = user.getUSER_ID();
                        //根据token 删除相应登录用户的在redis缓存中的用户信息
                        try{

                            String token = userMapper.getTokenByUserId(userId);
                            redisTemplate.delete(token);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        userMapper.deleteUserTokenByUid(userId);
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        try {
                            PrintWriter out = resp.getWriter();
                            Map logoutOutput = new HashMap();
                            if(authentication != null){
                                logoutOutput.put("AUTH",authentication);
                                logoutOutput.put("status",200);
                                logoutOutput.put("msg","您已退出登录！");
                            }else{
                                logoutOutput.put("status",500);
                                logoutOutput.put("errorMsg","您没有登录或token无效！");
                            }
                            ObjectMapper om = new ObjectMapper();
                            out.write(om.writeValueAsString(logoutOutput));
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .and()
                .csrf().disable()
                //配置自定义权限不足异常的请求返回值
                .exceptionHandling().accessDeniedHandler(deniedHandler);

    }

    /**
     * 实例化动态配置角色资源的类
     * @return
     */
    @Bean
    CustomFilterInvocationSecurityMetadataSource cfisms() {
        return new CustomFilterInvocationSecurityMetadataSource();
    }
    @Bean
    CustomAccessDecisionManager cadm() {
        return new CustomAccessDecisionManager();
    }

}

