package com.tony.tonna.entity;

import java.util.Date;

public class Message {
    private String MESSAGE_ID;
    private String MESSAGE_CONTENT;
    private Date CREATE_DATE;
    private String IS_DELETE;

    public String getMESSAGE_ID() {
        return MESSAGE_ID;
    }

    public void setMESSAGE_ID(String MESSAGE_ID) {
        this.MESSAGE_ID = MESSAGE_ID;
    }

    public String getMESSAGE_CONTENT() {
        return MESSAGE_CONTENT;
    }

    public void setMESSAGE_CONTENT(String MESSAGE_CONTENT) {
        this.MESSAGE_CONTENT = MESSAGE_CONTENT;
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
