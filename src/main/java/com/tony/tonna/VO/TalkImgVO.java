package com.tony.tonna.VO;

import java.util.Date;

public class TalkImgVO {
    private String TALK_ID;
    private String ARTICLE_ID;
    private String REPLY_ID;
    private String REPLY_CONTENT;
    private String OWNER_ID;
    private String USER_IMG;
    private String OWNER_NAME;
    private String TARGET_ID;
    private String TARGET_NAME;
    private Date CREATE_DATE;
    private String IS_DELETE;

    public String getTALK_ID() {
        return TALK_ID;
    }

    public void setTALK_ID(String TALK_ID) {
        this.TALK_ID = TALK_ID;
    }

    public String getARTICLE_ID() {
        return ARTICLE_ID;
    }

    public void setARTICLE_ID(String ARTICLE_ID) {
        this.ARTICLE_ID = ARTICLE_ID;
    }

    public String getREPLY_ID() {
        return REPLY_ID;
    }

    public void setREPLY_ID(String REPLY_ID) {
        this.REPLY_ID = REPLY_ID;
    }

    public String getREPLY_CONTENT() {
        return REPLY_CONTENT;
    }

    public void setREPLY_CONTENT(String REPLY_CONTENT) {
        this.REPLY_CONTENT = REPLY_CONTENT;
    }

    public String getOWNER_ID() {
        return OWNER_ID;
    }

    public void setOWNER_ID(String OWNER_ID) {
        this.OWNER_ID = OWNER_ID;
    }

    public String getUSER_IMG() {
        return USER_IMG;
    }

    public void setUSER_IMG(String USER_IMG) {
        this.USER_IMG = USER_IMG;
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
