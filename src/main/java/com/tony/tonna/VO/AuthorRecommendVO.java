package com.tony.tonna.VO;

public class AuthorRecommendVO {

    private String USER_ID;
    private String USER_NAME;
    private Integer ARTICLE_COUNT_NUM;
    private String USER_IMG;
    private String USER_INTRODUCE;

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

    public Integer getARTICLE_COUNT_NUM() {
        return ARTICLE_COUNT_NUM;
    }

    public void setARTICLE_COUNT_NUM(Integer ARTICLE_COUNT_NUM) {
        this.ARTICLE_COUNT_NUM = ARTICLE_COUNT_NUM;
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
}
