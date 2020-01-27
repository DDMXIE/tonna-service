package com.tony.tonna.entity;

import java.util.Date;

public class Article {

    private String ARTICLE_ID;
    private String ARTICLE_TITLE;
    private String ARTICLE_CONTENT;
    private String ARTICLE_CONTENT_HTML;
    private String ARTICLE_ORIGIN_USER_ID;
    private String ARTICLE_SECURITY;
    private String TYPE_ID;
    private String ARTICLE_STATUS;
    private Date CREATE_DATE;
    private Date UPDATE_DATE;
    private String IS_DELETE;

    public String getARTICLE_ID() {
        return ARTICLE_ID;
    }

    public void setARTICLE_ID(String ARTICLE_ID) {
        this.ARTICLE_ID = ARTICLE_ID;
    }

    public String getARTICLE_TITLE() {
        return ARTICLE_TITLE;
    }

    public void setARTICLE_TITLE(String ARTICLE_TITLE) {
        this.ARTICLE_TITLE = ARTICLE_TITLE;
    }

    public String getARTICLE_CONTENT() {
        return ARTICLE_CONTENT;
    }

    public void setARTICLE_CONTENT(String ARTICLE_CONTENT) {
        this.ARTICLE_CONTENT = ARTICLE_CONTENT;
    }

    public String getARTICLE_CONTENT_HTML() {
        return ARTICLE_CONTENT_HTML;
    }

    public void setARTICLE_CONTENT_HTML(String ARTICLE_CONTENT_HTML) {
        this.ARTICLE_CONTENT_HTML = ARTICLE_CONTENT_HTML;
    }

    public String getARTICLE_ORIGIN_USER_ID() {
        return ARTICLE_ORIGIN_USER_ID;
    }

    public void setARTICLE_ORIGIN_USER_ID(String ARTICLE_ORIGIN_USER_ID) {
        this.ARTICLE_ORIGIN_USER_ID = ARTICLE_ORIGIN_USER_ID;
    }

    public String getARTICLE_SECURITY() {
        return ARTICLE_SECURITY;
    }

    public void setARTICLE_SECURITY(String ARTICLE_SECURITY) {
        this.ARTICLE_SECURITY = ARTICLE_SECURITY;
    }

    public String getTYPE_ID() {
        return TYPE_ID;
    }

    public void setTYPE_ID(String TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

    public String getARTICLE_STATUS() {
        return ARTICLE_STATUS;
    }

    public void setARTICLE_STATUS(String ARTICLE_STATUS) {
        this.ARTICLE_STATUS = ARTICLE_STATUS;
    }

    public Date getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(Date CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public Date getUPDATE_DATE() {
        return UPDATE_DATE;
    }

    public void setUPDATE_DATE(Date UPDATE_DATE) {
        this.UPDATE_DATE = UPDATE_DATE;
    }

    public String getIS_DELETE() {
        return IS_DELETE;
    }

    public void setIS_DELETE(String IS_DELETE) {
        this.IS_DELETE = IS_DELETE;
    }
}
