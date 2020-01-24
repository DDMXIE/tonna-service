/**
 * Create by Tony
 * 2020.1.23 19:51
 * 文章笔记业务 Control
 */
package com.tony.tonna.control;

import com.tony.tonna.entity.Article;
import com.tony.tonna.service.ArticleService;
import com.tony.tonna.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 新增修改笔记
     * @param inputArticle
     * @return Result
     */
    @PostMapping("/admin/addUpdateArticle")
    public Result addUpdateArticle(@RequestBody Map inputArticle) throws ParseException {
        String flag = inputArticle.get("new").toString();
        Article article = new Article();
        article.setARTICLE_TITLE((String) inputArticle.get("title"));
        article.setARTICLE_CONTENT((String) inputArticle.get("contentMarkdown"));
        article.setARTICLE_CONTENT_HTML((String) inputArticle.get("contentHtml"));
        article.setTYPE_ID((String) inputArticle.get("articleType"));
        article.setARTICLE_ORIGIN_USER_ID((String) inputArticle.get("userId"));
        if(inputArticle.get("articleSecurity").equals(true)){
            article.setARTICLE_SECURITY("2");
        }else{
            article.setARTICLE_SECURITY("1");
        }
        if(flag.equals("add")){
            article.setARTICLE_ID(UUID.randomUUID().toString());
            article.setCREATE_DATE(new Date());
            article.setIS_DELETE("2");
        }else{
            article.setARTICLE_ID((String) inputArticle.get("articleId"));
            String time = inputArticle.get("createDate").toString();
            time = time.replace("Z", " UTC");//UTC是本地时间
            SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date createDate = format.parse(time);
            article.setCREATE_DATE(createDate);
            article.setUPDATE_DATE(new Date());
            article.setIS_DELETE((String) inputArticle.get("isDelete"));
        }
        return Result.success(200,articleService.addUpdateArticle(article,flag));
    }

    /**
     * 根据笔记id查询笔记详情
     * @param articleId
     * @return Result
     */
    @GetMapping("/admin/findArticleById")
    public Result findArticleById(@RequestParam(value = "articleId", required = false) String articleId){
        if(articleId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.findArticleById(articleId));
        }
    }
}
