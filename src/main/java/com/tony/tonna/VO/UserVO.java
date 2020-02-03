package com.tony.tonna.VO;

import com.tony.tonna.entity.Role;

import java.util.List;

public class UserVO {
    private String USER_ID;
    private String USER_NAME;
    private String USER_PASSWORD;
    private Boolean USER_ENABLED;
    private Boolean USER_LOCKED;
    private String USER_TEL;
    private String USER_IMG;
    private String USER_INTRODUCE;
    private String ROLE_ID;
    private String ROLE_NAME;
    private String ROLE_NAME_DCSP;

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

    public String getUSER_TEL() {
        return USER_TEL;
    }

    public void setUSER_TEL(String USER_TEL) {
        this.USER_TEL = USER_TEL;
    }

    public String getUSER_IMG() {
        return USER_IMG;
    }

    public void setUSER_IMG(String USER_IMG) {
        this.USER_IMG = USER_IMG;
    }

    public String getUSER_INTRODUCE() {
        return USER_INTRODUCE;
    }

    public void setUSER_INTRODUCE(String USER_INTRODUCE) {
        this.USER_INTRODUCE = USER_INTRODUCE;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME;
    }

    public String getROLE_NAME_DCSP() {
        return ROLE_NAME_DCSP;
    }

    public void setROLE_NAME_DCSP(String ROLE_NAME_DCSP) {
        this.ROLE_NAME_DCSP = ROLE_NAME_DCSP;
    }
}
