/**
 * Create by Tony
 * 2020.1.23 19:51
 * 文章笔记业务 Mapper
 */
package com.tony.tonna.mapper;

import com.tony.tonna.entity.Article;
import com.tony.tonna.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 根据作者id查询用户信息
     * @param authorId
     * @return
     */
    List<User> findAuthorById(@Param("authorId")String authorId);
}
