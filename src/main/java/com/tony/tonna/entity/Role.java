package com.tony.tonna.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private String ROLE_ID;
    private String ROLE_NAME;
    private String ROLE_NAME_DSCP;

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

    public String getROLE_NAME_DSCP() {
        return ROLE_NAME_DSCP;
    }

    public void setROLE_NAME_DSCP(String ROLE_NAME_DSCP) {
        this.ROLE_NAME_DSCP = ROLE_NAME_DSCP;
    }
}
