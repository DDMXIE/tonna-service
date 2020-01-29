/**
 * Create by Tony
 * 2020.1.29 12:26
 * 评论业务 Service
 */
package com.tony.tonna.service;

import com.tony.tonna.VO.TalkImgVO;
import com.tony.tonna.entity.Talk;
import com.tony.tonna.mapper.TalkMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TalkService {

    @Resource
    private TalkMapper talkMapper;

    /**
     * 新增或回复评论
     * @param talk
     * @return
     */
    public int addOrReplyTalk(Talk talk){
        return talkMapper.addOrReplyTalk(talk);
    }

    /**
     * 根据文章id获取文章评论
     * @param articleId
     * @return
     */
    public List findAllTalk(String articleId){
        List<TalkImgVO> list = talkMapper.findAllTalk(articleId);
        List newlist = new ArrayList();
        for (TalkImgVO talk:list) {
            Map mapitem = new HashMap();
            List<TalkImgVO> subList = talkMapper.findTalkByReplyId(talk.getTALK_ID());
            mapitem.put("talk",talk);
            if(subList.size()!= 0){
                mapitem.put("subTalk",subList);
            }
            newlist.add(mapitem);
        }
        return newlist;
    }
}
