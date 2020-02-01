package com.tony.tonna.VO;

import java.sql.Date;

public class ActivityVO {
    private String ACTIVITY_ID;
    private String USER_ID;
    private String ACTIVITY_STATUS;
    private String ARTICLE_ID;
    private String AUTHOR_ID;
    private Date CREATE_DATE;
    private String BUSINESS_ID;

    public String getACTIVITY_ID() {
        return ACTIVITY_ID;
    }

    public void setACTIVITY_ID(String ACTIVITY_ID) {
        this.ACTIVITY_ID = ACTIVITY_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getACTIVITY_STATUS() {
        return ACTIVITY_STATUS;
    }

    public void setACTIVITY_STATUS(String ACTIVITY_STATUS) {
        this.ACTIVITY_STATUS = ACTIVITY_STATUS;
    }

    public String getARTICLE_ID() {
        return ARTICLE_ID;
    }

    public void setARTICLE_ID(String ARTICLE_ID) {
        this.ARTICLE_ID = ARTICLE_ID;
    }

    public String getAUTHOR_ID() {
        return AUTHOR_ID;
    }

    public void setAUTHOR_ID(String AUTHOR_ID) {
        this.AUTHOR_ID = AUTHOR_ID;
    }

    public Date getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(Date CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public String getBUSINESS_ID() {
        return BUSINESS_ID;
    }

    public void setBUSINESS_ID(String BUSINESS_ID) {
        this.BUSINESS_ID = BUSINESS_ID;
    }
}
