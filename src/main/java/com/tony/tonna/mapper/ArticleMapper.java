/**
 * Create by Tony
 * 2020.1.23 19:51
 * 文章笔记业务 Mapper
 */
package com.tony.tonna.mapper;

import com.tony.tonna.VO.ActivityVO;
import com.tony.tonna.VO.ArticleCollectVO;
import com.tony.tonna.VO.ArticleHotVO;
import com.tony.tonna.VO.ArticleLikeVO;
import com.tony.tonna.entity.Article;
import com.tony.tonna.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ArticleMapper {
    /**
     * 根据笔记id删除相关笔记信息
     * @param articleId
     * @return int
     */
    int removeArticleById(@Param("articleId")String articleId);

    /**
     * 新增修改笔记
     * @param article
     * @return int
     */
    int addUpdateArticle(Article article);

    /**
     * 根据笔记id查询笔记详情
     * @param articleId
     * @return List
     */
    List<Article> findArticleById(@Param("articleId")String articleId);

    /**
     * 修改笔记状态
     * @param articleId
     * @param articleStatus
     * @return
     */
    int updateArticleStatus(@Param("articleId")String articleId,@Param("articleStatus")String articleStatus);

    /**
     * 分页查询所有笔记
     * @param typeId
     * @param start
     * @param end
     * @return
     */
    List<Article> findAllArticle(@Param("typeId")String typeId,@Param("start")int start,@Param("end")int end);

    /**
     * 模糊查询笔记信息
     * @param articleName
     * @return
     */
    List<Article> findArticleByName(@Param("articleName")String articleName);

    /**
     * 获取热度笔记
     * @return
     */
    List<ArticleHotVO> findHotArticle();

    /**
     * 根据作者id查询用户信息
     * @param authorId
     * @return
     */
    List<User> findAuthorById(@Param("authorId")String authorId);

    /**
     * 根据用户id和文章id查询用户是否点赞此篇文章
     * @param userId
     * @param articleId
     * @return ArticleLikeVO
     */
    List<ArticleLikeVO> findUserLikeByArticleId(@Param("userId")String userId,@Param("articleId")String articleId);

    /**
     * 用户增加点赞
     * @param articleLikeId
     * @param userId
     * @param articleId
     * @param createDate
     * @param isDelete
     * @return int
     */
    int likeArticleByUser(@Param("articleLikeId")String articleLikeId,@Param("userId")String userId,
                          @Param("articleId")String articleId,@Param("createDate") Date createDate,
                          @Param("isDelete")String isDelete);

    /**
     * 用户根据用户id和文章id删除赞（软删除）
     * @param userId
     * @param articleId
     * @return int
     */
    int deleteLike(@Param("userId")String userId,@Param("articleId")String articleId);

    /**
     * 根据文章id查询点赞数（未删除的）
     * @param articleId
     * @return int
     */
    int countLikeByArticleId(@Param("articleId")String articleId);

    /**
     * 根据用户id和文章id查询用户是否收藏此篇文章
     * @param userId
     * @param articleId
     * @return List
     */
    List<ArticleCollectVO> findUserCollectByArticleId(@Param("userId")String userId, @Param("articleId")String articleId);

    /**
     * 用户添加收藏
     * @param articleCollectId
     * @param userId
     * @param articleId
     * @param createDate
     * @param isDelete
     * @return int
     */
    int collectArticleByUser(@Param("articleCollectId")String articleCollectId,@Param("userId")String userId,
                          @Param("articleId")String articleId,@Param("createDate") Date createDate,
                          @Param("isDelete")String isDelete);

    /**
     * 用户根据用户id和文章id取消收藏（软删除）
     * @param userId
     * @param articleId
     * @return int
     */
    int deleteCollect(@Param("userId")String userId,@Param("articleId")String articleId);

    /**
     * 用户查看自己收藏的文章
     * @param userId
     * @return
     */
    List<Article> findCollectByUserId(@Param("userId")String userId);

    /**
     * 用户增加动态信息
     * @param activityId
     * @param userId
     * @param activityStatus
     * @param articleId
     * @param authorId
     * @param createDate
     * @param businessId
     * @return
     */
    int addActivityByUser(@Param("activityId")String activityId,@Param("userId")String userId,
                          @Param("activityStatus")String activityStatus,@Param("articleId") String articleId,
                          @Param("authorId")String authorId,@Param("createDate") Date createDate,
                          @Param("businessId")String businessId);

    /**
     * 分页查询用户状态
     * @param userId
     * @param start
     * @param end
     * @return
     */
    List<ActivityVO> findUserActivityByPage(@Param("userId")String userId, @Param("start")int start, @Param("end")int end);

    /**
     * 根据点赞id获取点赞信息
     * @param likeId
     * @return
     */
    List<ArticleLikeVO> findLikeByLikeId(@Param("likeId")String likeId);

    /**
     * 根据作者id分页获取所有文章
     * @param authorId
     * @param start
     * @param end
     * @return
     */
    List<Article> findAuthorArticle(@Param("authorId")String authorId,@Param("start")int start,@Param("end")int end);

    /**
     * 根据文章类型获取该类型文章总数（未删除）
     * @param typeId
     * @return
     */
    int findAriticleTypeNum(@Param("typeId")String typeId);

    /**
     * 后台-获取基础信息-获取笔记总数
     * @return
     */
    int countArticleNum();

    /**
     * 后台-获取基础信息-获取收藏总数
     * @return
     */
    int countCollectNum();
}
