/**
 * Create by Tony
 * 2020.1.29 12:26
 * 评论业务 Mapper
 */
package com.tony.tonna.mapper;

import com.tony.tonna.VO.ArticleLikeVO;
import com.tony.tonna.VO.TalkImgVO;
import com.tony.tonna.VO.TalkSuperVO;
import com.tony.tonna.entity.Talk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TalkMapper {

    /**
     * 新增或回复评论
     * @param talk
     * @return int
     */
    int addOrReplyTalk(Talk talk);

    /**
     * 根据文章id获取帖子评论（一级评论）
     * @param articleId
     * @return
     */
    List<TalkImgVO> findAllTalk(@Param("articleId")String articleId);

    /**
     * 根据评论id获取子评论（回复）
     * @param replyId
     * @return
     */
    List<TalkImgVO> findTalkByReplyId(@Param("replyId")String replyId);

    /**
     * 根据文章id查询评论数（未删除的）
     * @param articleId
     * @return
     */
    int countTalkByArticleId(@Param("articleId")String articleId);

    /**
     * 根据评论id获取评论信息
     * @param talkId
     * @return
     */
    List<TalkImgVO> findTalkByTalkId(@Param("talkId")String talkId);

    /**
     *后台-获取基础信息-获取评论总量
     * @return
     */
    int countTalkNum();

    /**
     * 管理员获取全部评论
     * @return
     */
    List<TalkSuperVO> findAllTalkBySuper();

    /**
     * 管理员删除评论
     * @param talkId
     * @return
     */
    int deleteTalkBySuper(@Param("talkId")String talkId);

    /**
     * 管理员修改评论信息
     * @param talkId
     * @param replyContent
     * @return
     */
    int updateTalkBySuper(@Param("talkId")String talkId,@Param("replyContent")String replyContent);
}
