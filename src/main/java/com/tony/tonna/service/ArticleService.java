/**
 * Create by Tony
 * 2020.1.23 19:51
 * 文章笔记业务 Serive
 */
package com.tony.tonna.service;

import com.tony.tonna.entity.Article;
import com.tony.tonna.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    /**
     * 新增修改笔记
     * @param article
     * @param flag
     * @return Map
     */
    public Map addUpdateArticle(Article article,String flag){
        Map outputdata = new HashMap();
        if(flag.equals("update")){
            articleMapper.removeArticleById(article.getARTICLE_ID());
        }
        int num = articleMapper.addUpdateArticle(article);
        if(num == 1){
            if(flag.equals("add")){
                outputdata.put("tip","您已成功添加笔记!");
            }else if(flag.equals("update")){
                outputdata.put("tip","您已成功修改笔记!");
            }
        }
        outputdata.put("num",num);
        outputdata.put("id",article.getARTICLE_ID());
        return outputdata;
    }

    /**
     * 根据笔记id查询笔记详情
     * @param articleId
     * @return List
     */
    public List<Article> findArticleById(String articleId){
        return articleMapper.findArticleById(articleId);
    }
}
