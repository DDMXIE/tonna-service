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
     * 模糊查询笔记信息
     * @return Result
     */
    @GetMapping("/tonna/findArticleByName")
    public Result findArticleByName(@RequestParam(value = "articleName", required = false) String articleName){
        if(articleName == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.findArticleByName(articleName));
        }

    }

    /**
     * 获取热度笔记
     * @return Result
     */
    @GetMapping("/tonna/findHotArticle")
    public Result findHotArticle(){
        return Result.success(200,articleService.findHotArticle());
    }

    /**
     * 获取作者推荐
     * @return Result
     */
    @GetMapping("/tonna/findRecommendAuthor")
    public Result findRecommendAuthor(){
        return Result.success(200,articleService.findRecommendAuthor());
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

    /**
     * 用户查看自己收藏的文章
     * @param userId
     * @return
     */
    @GetMapping("/admin/findCollectByUserId")
    public Result findCollectByUserId(@RequestParam(value = "userId", required = false) String userId){
        if(userId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.findCollectByUserId(userId));
        }
    }

    /**
     * 用户根据id查询用户动态
     * @param userId
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/tonna/findUserActivityByPage")
    public Result findUserActivityByPage(@RequestParam(value = "userId", required = false) String userId,
                                   @RequestParam(value = "start", required = false) int start,
                                   @RequestParam(value = "end", required = false) int end){
        return Result.success(200,articleService.findUserActivityByPage(userId,start,end));
    }

    /**
     * 加载用户主页文章内容
     * @param authorId
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/tonna/findAuthorArticle")
    public Result findAuthorArticle(@RequestParam(value = "authorId", required = false) String authorId,
                                    @RequestParam(value = "start", required = false) int start,
                                    @RequestParam(value = "end", required = false) int end){
        if(authorId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.findAuthorArticle(authorId,start,end));
        }
    }

    /**
     * 后台-加载文章类型和统计数量
     * @return Result
     */
    @GetMapping("/super/findAriticleTypeNum")
    public Result findAriticleTypeNum(){
        return Result.success(200,articleService.findAriticleTypeNum());
    }

    /**
     * 管理员分页获取笔记信息
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/super/findAllArticleByPage")
    public Result findAllArticleByPage( @RequestParam(value = "start", required = false) Integer start,
                                  @RequestParam(value = "end", required = false) Integer end){
        return Result.success(200,articleService.findAllArticleByPage(start,end));
    }

    /**
     * 管理员修改笔记信息
     * @param inputData
     * @return
     */
    @PostMapping("/super/updateArticleBySuper")
    public Result updateArticleBySuper(@RequestBody Map inputData){
        return Result.success(200,articleService.updateArticleBySuper(inputData));
    }

    /**
     * 管理员审核文章
     * @param articleId
     * @param articleStatus
     * @return
     */
    @GetMapping("/super/checkArticleBySuper")
    public Result checkArticleBySuper(@RequestParam(value = "articleId", required = false) String articleId,
                               @RequestParam(value = "articleStatus", required = false) String articleStatus){
        if(articleId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.checkArticleBySuper(articleId,articleStatus));
        }
    }

    /**
     * 管理员删除文章（软删）
     * @param articleId
     * @return
     */
    @GetMapping("/super/deleteArticleBySuper")
    public Result deleteArticleBySuper(@RequestParam(value = "articleId", required = false) String articleId){
        if(articleId == null){
            return Result.fail();
        }else{
            return Result.success(200,articleService.deleteArticleBySuper(articleId));
        }
    }
}
