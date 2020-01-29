/**
 * Create by Tony
 * 2020.1.29 12:26
 * 评论业务 Control
 */
package com.tony.tonna.control;

import com.tony.tonna.entity.Talk;
import com.tony.tonna.service.TalkService;
import com.tony.tonna.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class TalkController {

    @Resource
    private TalkService talkService;

    /**
     * 用户新增或回复评论
     * @param inputTalk
     */
    @PostMapping("/admin/addOrReplyTalk")
    public Result addOrReplyTalk(@RequestBody Map inputTalk){
        Map outputdata = new HashMap();
        Talk talk = new Talk();
        talk.setTALK_ID(UUID.randomUUID().toString());
        talk.setARTICLE_ID(inputTalk.get("articleId").toString());
        talk.setREPLY_CONTENT(inputTalk.get("replyContent").toString());
        talk.setOWNER_ID(inputTalk.get("ownerId").toString());
        talk.setOWNER_NAME(inputTalk.get("ownerName").toString());
        talk.setCREATE_DATE(new Date());
        talk.setIS_DELETE("2");
        if(!inputTalk.get("replyId").equals("0")){
            talk.setREPLY_ID(inputTalk.get("replyId").toString());
            talk.setTARGET_ID(inputTalk.get("targetId").toString());
            talk.setTARGET_NAME(inputTalk.get("targetName").toString());
            outputdata.put("tip","您已回复评论!");
        }else{
            talk.setREPLY_ID("0");
            outputdata.put("tip","您已新增评论!");
        }
        int num = talkService.addOrReplyTalk(talk);
        outputdata.put("num",num);
        return Result.success(200,outputdata);
    }

    /**
     * 根据文章id获取文章评论
     * @param articleId
     * @return
     */
    @GetMapping("/tonna/findAllTalk")
    public Result findAllTalk(@RequestParam(value = "articleId", required = false) String articleId){
        if(articleId == null){
            return Result.fail();
        }else{
            return Result.success(200,talkService.findAllTalk(articleId));
        }
    }
}
