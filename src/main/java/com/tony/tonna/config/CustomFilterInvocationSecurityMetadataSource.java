package com.tony.tonna.config;

import com.tony.tonna.entity.Role;
import com.tony.tonna.entity.Source;
import com.tony.tonna.mapper.SourceMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();  //用来实现ant风格URL的匹配
    @Resource
    SourceMapper sourceMapper;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        //获取当前请求的URL
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //从数据库获取资源信息，即Source表以及Source表所对应的role，也可以将资源信息缓存在Redis中
        List<Source> allMenus = sourceMapper.getAllSources();
        //遍历资源信息，遍历过程中获取当前请求的URL所需要的角色并返回。
        //如果当前请求的URL在资源表中不存在相应的模式，就假设该请求登录后即可访问，返回ROLE_LOGIN
        for (Source source : allMenus) {
            if (antPathMatcher.match(source.getSOURCE_PATTERN(), requestUrl)) {
                List<Role> roles = source.getSOURCE_ROLES();
                String[] roleArr = new String[roles.size()];
                for (int i = 0; i < roleArr.length; i++) {
                    roleArr[i] = roles.get(i).getROLE_NAME();
                }
                return SecurityConfig.createList(roleArr);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    } //getAllConfigAttributes方法用来返回定义好的权限资源，SpringSecurity在启动时会校验相关配置是否正确，该方法直接返回null即可
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
    //supports方法返回类对象是否支持校验
}
