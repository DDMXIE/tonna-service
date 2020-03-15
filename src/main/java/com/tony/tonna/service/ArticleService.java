/**
 * Create by Tony
 * 2020.1.23 19:51
 * 文章笔记业务 Serive
 */
package com.tony.tonna.service;

import com.tony.tonna.VO.*;
import com.tony.tonna.entity.Article;
import com.tony.tonna.entity.Role;
import com.tony.tonna.entity.User;
import com.tony.tonna.mapper.ArticleMapper;
import com.tony.tonna.mapper.TalkMapper;
import com.tony.tonna.mapper.UserMapper;
import com.tony.tonna.mapper.UtilMapper;
import com.tony.tonna.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UtilService utilService;

    @Resource
    private TalkService talkService;

    @Resource
    private TalkMapper talkMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UtilMapper utilMapper;

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
        outputdata.put("createDate",article.getCREATE_DATE());
        outputdata.put("articleStatus",article.getARTICLE_STATUS());
        outputdata.put("isDelete",article.getIS_DELETE());
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

    /**
     * 发布笔记
     * @param articleId
     * @return
     */
    public Map publishedAriticle(String articleId){
        Map outputData = new HashMap();
        articleMapper.updateArticleStatus(articleId,"1");
        List list = articleMapper.findArticleById(articleId);
        outputData.put("data",list);
        return outputData;
    }

    /**
     * 获取所有笔记信息
     * @return
     */
    public List<ArticleFindAllVO> findAllArticle(String typeId,int start,int end){
//        List<Article> list = articleMapper.findArticleById("");
        List<Article> list = articleMapper.findAllArticle(typeId,start,end);
        List<ArticleFindAllVO> newlist = new ArrayList<>();
        for(Article article : list){
            ArticleFindAllVO articleFindAllVO = new ArticleFindAllVO();
            articleFindAllVO.setARTICLE(article);
            articleFindAllVO.setIMG_URL(utilService.getImgStr(article.getARTICLE_CONTENT_HTML()));
            articleFindAllVO.setARTICLE_INTRODUCE(utilService.removeHtml(article.getARTICLE_CONTENT_HTML()));
            List userlist = articleMapper.findAuthorById(article.getARTICLE_ORIGIN_USER_ID());
            User user = (User) userlist.get(0);
            articleFindAllVO.setARTICLE_AUTHOR(user.getUSER_NAME());
            articleFindAllVO.setTALK_NUM(talkMapper.countTalkByArticleId(article.getARTICLE_ID()));
            articleFindAllVO.setLIKE_NUM(articleMapper.countLikeByArticleId(article.getARTICLE_ID()));
            newlist.add(articleFindAllVO);
        }
        return newlist;
    }

    /**
     * 模糊查询笔记信息
     * @return
     */
    public List<ArticleFindAllVO> findArticleByName(String articleName){
//        List<Article> list = articleMapper.findArticleById("");
        List<Article> list = articleMapper.findArticleByName(articleName);
        List<ArticleFindAllVO> newlist = new ArrayList<>();
        for(Article article : list){
            ArticleFindAllVO articleFindAllVO = new ArticleFindAllVO();
            articleFindAllVO.setARTICLE(article);
            articleFindAllVO.setIMG_URL(utilService.getImgStr(article.getARTICLE_CONTENT_HTML()));
            articleFindAllVO.setARTICLE_INTRODUCE(utilService.removeHtml(article.getARTICLE_CONTENT_HTML()));
            List userlist = articleMapper.findAuthorById(article.getARTICLE_ORIGIN_USER_ID());
            User user = (User) userlist.get(0);
            articleFindAllVO.setARTICLE_AUTHOR(user.getUSER_NAME());
            articleFindAllVO.setTALK_NUM(talkMapper.countTalkByArticleId(article.getARTICLE_ID()));
            articleFindAllVO.setLIKE_NUM(articleMapper.countLikeByArticleId(article.getARTICLE_ID()));
            newlist.add(articleFindAllVO);
        }
        return newlist;
    }

    /**
     * 获取热度笔记
     * @return
     */
    public List<ArticleHotVO> findHotArticle(){
        return articleMapper.findHotArticle();
    }

    /**
     * 获取作者推荐
     * @return
     */
    public List<AuthorRecommendVO> findRecommendAuthor(){
        return articleMapper.findRecommendAuthor();
    }

    /**
     * 用户根据id获取笔记信息
     * @param articleId
     * @return
     */
    public List<ArticleFindAllVO> findAritcleByIdUser(String articleId){
        List<Article> list =  articleMapper.findArticleById(articleId);
        List<ArticleFindAllVO> newlist = new ArrayList<>();
        for(Article article : list){
            ArticleFindAllVO articleFindAllVO = new ArticleFindAllVO();
            articleFindAllVO.setARTICLE(article);
            articleFindAllVO.setIMG_URL(utilService.getImgStr(article.getARTICLE_CONTENT_HTML()));
            articleFindAllVO.setARTICLE_INTRODUCE(utilService.removeHtml(article.getARTICLE_CONTENT_HTML()));
            List userlist = articleMapper.findAuthorById(article.getARTICLE_ORIGIN_USER_ID());
            User user = (User) userlist.get(0);
            articleFindAllVO.setARTICLE_AUTHOR(user.getUSER_NAME());
            articleFindAllVO.setUSER_IMG(user.getUSER_IMG());
            articleFindAllVO.setTALK_NUM(talkMapper.countTalkByArticleId(article.getARTICLE_ID()));
            articleFindAllVO.setLIKE_NUM(articleMapper.countLikeByArticleId(article.getARTICLE_ID()));
            newlist.add(articleFindAllVO);
        }
        return newlist;
    }

    /**
     * 用户添加或删除赞
     * @param inputData
     * @return
     */
    public Map likeArticleByUser(Map inputData){
        Map outputdata = new HashMap();
        int num = 0;
        String userId = inputData.get("userId").toString();
        String articleId = inputData.get("articleId").toString();
        if(inputData.get("islike").equals("like")){
            List<ArticleLikeVO> likeList = articleMapper.findUserLikeByArticleId(userId,articleId);
            if(likeList.size()==0){
                String articleLikeId = UUID.randomUUID().toString();
                Date createDate = new Date();
                String isDelete = "2";
                num =  articleMapper.likeArticleByUser(articleLikeId,userId,articleId,createDate,isDelete);
                articleMapper.addActivityByUser(UUID.randomUUID().toString(),userId,"2",articleId,null,new Date(),articleLikeId);
                outputdata.put("tip","您已点赞成功!");
            }else{
                outputdata.put("tip","点赞失败！你已点过赞");
            }
        }else if(inputData.get("islike").equals("unlike")) {
            num = articleMapper.deleteLike(userId,articleId);
            outputdata.put("tip","您已取消赞！");
        }
        outputdata.put("num",num);
        return outputdata;
    }

    /**
     * 用户查看此篇文章点赞情况
     * @param userId
     * @param articleId
     * @return List
     */
    public List<ArticleLikeVO> findUserLikeByArticleId(String userId,String articleId){
        return articleMapper.findUserLikeByArticleId(userId,articleId);
    }

    /**
     * 用户添加或取消收藏
     * @param inputData
     * @return Map
     */
    public Map collectArticleByUser(Map inputData){
        Map outputdata = new HashMap();
        int num = 0;
        String userId = inputData.get("userId").toString();
        String articleId = inputData.get("articleId").toString();
        if(inputData.get("iscollect").equals("collect")){
            List<ArticleCollectVO> collectList = articleMapper.findUserCollectByArticleId(userId,articleId);
            if(collectList.size()==0){
                String articleCollectId = UUID.randomUUID().toString();
                Date createDate = new Date();
                String isDelete = "2";
                num =  articleMapper.collectArticleByUser(articleCollectId,userId,articleId,createDate,isDelete);
//                articleMapper.addActivityByUser(UUID.randomUUID().toString(),userId,"3",articleId,null,new Date(),articleCollectId);
                outputdata.put("tip","您已收藏成功!");
            }else{
                outputdata.put("tip","收藏失败！你已收藏");
            }
        }else if(inputData.get("iscollect").equals("uncollect")) {
            num = articleMapper.deleteCollect(userId,articleId);
            outputdata.put("tip","您已取消收藏！");
        }
        outputdata.put("num",num);
        return outputdata;
    }

    /**
     * 用户查看此篇文章收藏情况
     * @param userId
     * @param articleId
     * @return List
     */
    public List<ArticleCollectVO> findUserCollectByAticleId(String userId,String articleId){
        return articleMapper.findUserCollectByArticleId(userId,articleId);
    }

    /**
     * 用户查看自己收藏的文章
     * @param userId
     * @return List
     */
    public List<ArticleFindAllVO> findCollectByUserId(String userId){
        List<Article> myCollect =  articleMapper.findCollectByUserId(userId);
        List<ArticleFindAllVO> newlist = new ArrayList<>();
        for(Article article : myCollect){
            ArticleFindAllVO articleFindAllVO = new ArticleFindAllVO();
            articleFindAllVO.setARTICLE(article);
            articleFindAllVO.setIMG_URL(utilService.getImgStr(article.getARTICLE_CONTENT_HTML()));
            articleFindAllVO.setARTICLE_INTRODUCE(utilService.removeHtml(article.getARTICLE_CONTENT_HTML()));
            List userlist = articleMapper.findAuthorById(article.getARTICLE_ORIGIN_USER_ID());
            User user = (User) userlist.get(0);
            articleFindAllVO.setARTICLE_AUTHOR(user.getUSER_NAME());
            articleFindAllVO.setTALK_NUM(talkMapper.countTalkByArticleId(article.getARTICLE_ID()));
            articleFindAllVO.setLIKE_NUM(articleMapper.countLikeByArticleId(article.getARTICLE_ID()));
            newlist.add(articleFindAllVO);
        }
        return newlist;
    }

    /**
     * 用户根据用户id查询动态
     * @param userId
     * @param start
     * @param end
     * @return
     */
    public List findUserActivityByPage(String userId,int start,int end){
        List userActivityList = new ArrayList();
        List<ActivityVO> activityList = articleMapper.findUserActivityByPage(userId,start,end);
        for (ActivityVO activity:activityList) {
            Map userActivity = new HashMap();
            userActivity.put("userInfo",userMapper.findUserInfoByUserId(activity.getUSER_ID()));
            userActivity.put("activityType",activity.getACTIVITY_STATUS());
            switch (activity.getACTIVITY_STATUS()){
                case "1":
                    List<TalkImgVO> talkData = talkMapper.findTalkByTalkId(activity.getBUSINESS_ID());
                    if(talkData.size()!=0){
                        userActivity.put("talkData",talkData);
                        userActivity.put("talkArticleData",this.findAritcleByIdUser(talkData.get(0).getARTICLE_ID()));
                    }else{
                        userActivity.put("talkData",null);
                    }
                    userActivityList.add(userActivity);
                    break;

                case "2":
                    List<ArticleLikeVO> likeData = articleMapper.findLikeByLikeId(activity.getBUSINESS_ID());
                    if(likeData.size()!=0){
                        userActivity.put("likeData",likeData);
                        userActivity.put("likeAriticleData",this.findAritcleByIdUser(likeData.get(0).getARTICLE_ID()));
                    }else{
                        userActivity.put("likeData",null);
                    }
                    userActivityList.add(userActivity);
                    break;

                case "3":
                    List<AttentionVO> attentionData = userMapper.findAttentionByAttentionId(activity.getBUSINESS_ID());
                    if(attentionData.size()!=0){
                        userActivity.put("attentionData",attentionData);
                        userActivity.put("attentionUserData",userMapper.findUserInfoByUserId(attentionData.get(0).getTARGET_ID()));
                    }else{
                        userActivity.put("attentionData",null);
                    }
                    userActivityList.add(userActivity);
                    break;
            }

        }
        return userActivityList;
    }

    /**
     * 加载用户主页文章内容
     * @param authorId
     * @param start
     * @param end
     * @return
     */
    public List findAuthorArticle(String authorId,int start,int end){
        List<Article> list = articleMapper.findAuthorArticle(authorId,start,end);
        List<ArticleFindAllVO> newlist = new ArrayList<>();
        for(Article article : list){
            ArticleFindAllVO articleFindAllVO = new ArticleFindAllVO();
            articleFindAllVO.setARTICLE(article);
            articleFindAllVO.setIMG_URL(utilService.getImgStr(article.getARTICLE_CONTENT_HTML()));
            articleFindAllVO.setARTICLE_INTRODUCE(utilService.removeHtml(article.getARTICLE_CONTENT_HTML()));
            List userlist = articleMapper.findAuthorById(article.getARTICLE_ORIGIN_USER_ID());
            User user = (User) userlist.get(0);
            articleFindAllVO.setARTICLE_AUTHOR(user.getUSER_NAME());
            articleFindAllVO.setUSER_IMG(user.getUSER_IMG());
            articleFindAllVO.setTALK_NUM(talkMapper.countTalkByArticleId(article.getARTICLE_ID()));
            articleFindAllVO.setLIKE_NUM(articleMapper.countLikeByArticleId(article.getARTICLE_ID()));
            newlist.add(articleFindAllVO);
        }
        return newlist;
    }

    /**
     * 后台-加载文章类型和统计数量
     * @return List
     */
    public List findAriticleTypeNum(){
        List outputdata = new ArrayList();
        List<ConstTypeVO> constTypeVOList = utilMapper.findConstType("ARTICLE_TYPE");
        for (ConstTypeVO constTypeVO:constTypeVOList) {
            Map map = new HashMap();
            map.put("typeId",constTypeVO.getTYPE_ID());
            map.put("typeName",constTypeVO.getTYPE_NAME());
            map.put("num",articleMapper.findAriticleTypeNum(constTypeVO.getTYPE_ID()));
            outputdata.add(map);
        }
        return outputdata;
    }

    /**
     * 管理员分页获取笔记信息
     * @param start
     * @param end
     * @return
     */
    public Map findAllArticleByPage(int start,int end){
        Map outputData = new HashMap();
        List<Article> list =  articleMapper.findArticleById("");
        List<ArticleFindAllVO> newlist = new ArrayList<>();
        for(Article article : list){
            ArticleFindAllVO articleFindAllVO = new ArticleFindAllVO();
            articleFindAllVO.setARTICLE(article);
            articleFindAllVO.setIMG_URL(utilService.getImgStr(article.getARTICLE_CONTENT_HTML()));
            articleFindAllVO.setARTICLE_INTRODUCE(utilService.removeHtml(article.getARTICLE_CONTENT_HTML()));
            List userlist = articleMapper.findAuthorById(article.getARTICLE_ORIGIN_USER_ID());
            User user = (User) userlist.get(0);
            articleFindAllVO.setARTICLE_AUTHOR(user.getUSER_NAME());
            articleFindAllVO.setUSER_IMG(user.getUSER_IMG());
            articleFindAllVO.setTALK_NUM(talkMapper.countTalkByArticleId(article.getARTICLE_ID()));
            articleFindAllVO.setLIKE_NUM(articleMapper.countLikeByArticleId(article.getARTICLE_ID()));
            newlist.add(articleFindAllVO);
        }
        int firstIndex = (start - 1) * end;
        int lastIndex = start * end;
        if(lastIndex > newlist.size()){
            lastIndex = firstIndex+(lastIndex - newlist.size());
        }
        outputData.put("total",newlist.size());
        outputData.put("userList",newlist.subList(firstIndex,lastIndex));
        return outputData;
    }


}
