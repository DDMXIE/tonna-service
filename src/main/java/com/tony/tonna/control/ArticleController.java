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
            article.setIS_DELETE("2"); //2为未删除 1为删除
            article.setARTICLE_STATUS("2"); //2为草稿 1为发表
        }else{
            article.setARTICLE_ID((String) inputArticle.get("articleId"));
            String time = inputArticle.get("createDate").toString();
            time = time.replace("Z", " UTC");//UTC是本地时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date createDate = format.parse(time);
            article.setCREATE_DATE(createDate);
            article.setUPDATE_DATE(new Date());
            article.setIS_DELETE((String) inputArticle.get("isDelete"));
            article.setARTICLE_STATUS((String) inputArticle.get("articleStatus")); //2为草稿 1为发表
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

    /**
     * 发布笔记
     * @param articleId
     * @return
     */
    @GetMapping("/admin/publishedAriticle")
    public Result publishedAriticle(@RequestParam(value = "articleId", required = false) String articleId){
        if(articleId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.publishedAriticle(articleId));
        }
    }

    /**
     * 查询所有笔记
     * @return Result
     */
    @GetMapping("/tonna/findAllArticle")
    public Result findAllArticle(@RequestParam(value = "typeId", required = false) String typeId,
                                 @RequestParam(value = "start", required = false) int start,
                                 @RequestParam(value = "end", required = false) int end){
        if(typeId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.findAllArticle(typeId,start,end));
        }

    }

    /**
     * 用户根据id获取笔记信息
     * @param articleId
     * @return Result
     */
    @GetMapping("/tonna/findAritcleByIdUser")
    public Result findAritcleByIdUser(@RequestParam(value = "articleId", required = false) String articleId){
        if(articleId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.findAritcleByIdUser(articleId));
        }
    }

    /**
     * 用户新增或删除赞
     * @param inputData
     * @return Result
     */
    @PostMapping("/admin/likeArticleByUser")
    public Result likeArticleByUser(@RequestBody Map inputData){
        return Result.success(200,articleService.likeArticleByUser(inputData));
    }

    /**
     * 用户查看此篇文章点赞情况
     * @param userId
     * @param articleId
     * @return
     */
    @GetMapping("/tonna/findUserLikeByAticleId")
    public Result findUserLikeByAticleId(@RequestParam(value = "userId", required = false) String userId,
                                         @RequestParam(value = "articleId", required = false) String articleId){
        if(userId == null || articleId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.findUserLikeByArticleId(userId,articleId));
        }
    }

    /**
     * 用户新增或取消收藏
     * @param inputData
     * @return Result
     */
    @PostMapping("/admin/collectArticleByUser")
    public Result collectArticleByUser(@RequestBody Map inputData){
        return Result.success(200,articleService.collectArticleByUser(inputData));
    }

    /**
     * 用户查看此篇文章收藏情况
     * @param userId
     * @param articleId
     * @return
     */
    @GetMapping("/tonna/findUserCollectByAticleId")
    public Result findUserCollectByAticleId(@RequestParam(value = "userId", required = false) String userId,
                                         @RequestParam(value = "articleId", required = false) String articleId){
        if(userId == null || articleId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.findUserCollectByAticleId(userId,articleId));
        }
    }
}
