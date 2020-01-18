package com.tony.tonna.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails, Serializable {
    private String USER_ID;
    private String USER_NAME;
    private String USER_PASSWORD;
    private Boolean USER_ENABLED;
    private Boolean USER_LOCKED;
    private String USER_TEL;
    private List<Role> ROLES; /** 相当于关联Role **/
//    private String token;

    /**
     * getAuthorities 该方法用于获取当前用户的角色信息
     * 角色信息存储在role属性中，直接遍历role属性，然后构造SimpleGrantedAuthority集合并返回
     * @return Collection
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:ROLES
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getROLE_NAME()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return USER_PASSWORD;
    }

    @Override
    public String getUsername() {
        return USER_NAME;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !USER_LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return USER_ENABLED;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }

    public Boolean getUSER_ENABLED() {
        return USER_ENABLED;
    }

    public void setUSER_ENABLED(Boolean USER_ENABLED) {
        this.USER_ENABLED = USER_ENABLED;
    }

    public Boolean getUSER_LOCKED() {
        return USER_LOCKED;
    }

    public void setUSER_LOCKED(Boolean USER_LOCKED) {
        this.USER_LOCKED = USER_LOCKED;
    }

    public List<Role> getROLES() {
        return ROLES;
    }

    public void setROLES(List<Role> ROLES) {
        this.ROLES = ROLES;
    }

    public String getUSER_TEL() {
        return USER_TEL;
    }

    public void setUSER_TEL(String USER_TEL) {
        this.USER_TEL = USER_TEL;
    }
}
