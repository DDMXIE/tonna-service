/**
 * Create by Tony
 * 2020.1.23 19:51
 * 文章笔记业务 Mapper
 */
package com.tony.tonna.mapper;

import com.tony.tonna.entity.Article;
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
}
