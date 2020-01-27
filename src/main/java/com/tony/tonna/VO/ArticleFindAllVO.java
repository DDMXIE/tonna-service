package com.tony.tonna.VO;

import com.tony.tonna.entity.Article;

import java.util.Date;
import java.util.Set;

public class ArticleFindAllVO {
   private Article ARTICLE;
   private Set IMG_URL;
   private String ARTICLE_INTRODUCE;
   private String ARTICLE_AUTHOR;

    public Article getARTICLE() {
        return ARTICLE;
    }

    public void setARTICLE(Article ARTICLE) {
        this.ARTICLE = ARTICLE;
    }

    public Set getIMG_URL() {
        return IMG_URL;
    }

    public void setIMG_URL(Set IMG_URL) {
        this.IMG_URL = IMG_URL;
    }

    public String getARTICLE_INTRODUCE() {
        return ARTICLE_INTRODUCE;
    }

    public void setARTICLE_INTRODUCE(String ARTICLR_INTRODUCE) {
        this.ARTICLE_INTRODUCE = ARTICLR_INTRODUCE;
    }

    public String getARTICLE_AUTHOR() {
        return ARTICLE_AUTHOR;
    }

    public void setARTICLE_AUTHOR(String ARTICLE_AUTHOR) {
        this.ARTICLE_AUTHOR = ARTICLE_AUTHOR;
    }
}
