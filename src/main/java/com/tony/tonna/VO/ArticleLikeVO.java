package com.tony.tonna.VO;

import java.util.Date;

public class ArticleLikeVO {
    private String ARTICLE_LIKE_ID;
    private String USER_ID;
    private String USER_NAME;
    private String ARTICLE_ID;
    private Date CREATE_DATE;
    private String IS_DELETE;

    public String getARTICLE_LIKE_ID() {
        return ARTICLE_LIKE_ID;
    }

    public void setARTICLE_LIKE_ID(String ARTICLE_LIKE_ID) {
        this.ARTICLE_LIKE_ID = ARTICLE_LIKE_ID;
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

    public String getARTICLE_ID() {
        return ARTICLE_ID;
    }

    public void setARTICLE_ID(String ARTICLE_ID) {
        this.ARTICLE_ID = ARTICLE_ID;
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
