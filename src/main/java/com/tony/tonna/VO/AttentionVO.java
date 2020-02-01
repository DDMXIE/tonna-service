package com.tony.tonna.VO;

import java.util.Date;

public class AttentionVO {
    private String ATTENTION_ID;
    private String OWNER_ID;
    private String OWNER_NAME;
    private String TARGET_ID;
    private String TARGET_NAME;
    private Date CREATE_DATE;
    private String IS_DELETE;

    public String getATTENTION_ID() {
        return ATTENTION_ID;
    }

    public void setATTENTION_ID(String ATTENTION_ID) {
        this.ATTENTION_ID = ATTENTION_ID;
    }

    public String getOWNER_ID() {
        return OWNER_ID;
    }

    public void setOWNER_ID(String OWNER_ID) {
        this.OWNER_ID = OWNER_ID;
    }

    public String getOWNER_NAME() {
        return OWNER_NAME;
    }

    public void setOWNER_NAME(String OWNER_NAME) {
        this.OWNER_NAME = OWNER_NAME;
    }

    public String getTARGET_ID() {
        return TARGET_ID;
    }

    public void setTARGET_ID(String TARGET_ID) {
        this.TARGET_ID = TARGET_ID;
    }

    public String getTARGET_NAME() {
        return TARGET_NAME;
    }

    public void setTARGET_NAME(String TARGET_NAME) {
        this.TARGET_NAME = TARGET_NAME;
    }

    public Date getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(Date CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public String getIS_DELETE() {
        return IS_DELETE;
    }

    public void setIS_DELETE(String IS_DELETE) {
        this.IS_DELETE = IS_DELETE;
    }
}
