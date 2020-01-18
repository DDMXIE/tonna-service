package com.tony.tonna.control;

import com.tony.tonna.entity.Message;
import com.tony.tonna.service.MessageService;
import com.tony.tonna.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class MessageController {
    @Resource
    private MessageService messageService;

    /**
     * 新增用户留言
     * @param inputMessage
     */
    @PostMapping("/admin/addMessageByAdmin")
    public Result addMessageByAdmin(@RequestBody Map inputMessage){
        Message message = new Message();
        String userId = (String) inputMessage.get("userId");
        message.setMESSAGE_ID(UUID.randomUUID().toString());
        message.setMESSAGE_CONTENT((String) inputMessage.get("messageContent"));
        message.setCREATE_DATE(new Date());
        message.setIS_DELETE("2");
        int num = messageService.addMessageByAdmin(userId,message);
        Map outputdata = new HashMap();
        outputdata.put("tip","您已成功添加留言!");
        outputdata.put("num",num);
        return Result.success(200,outputdata);
    }

    /**
     * 查询留言数据（可根据用户查询）
     * @param userId
     * @return Result
     */
    @GetMapping("/admin/findMessageByAdmin")
    public Result findMessageByAdmin(@RequestParam(value = "userId", required = false) String userId){
        if(userId == null){
            return Result.fail();
        }else{
            return Result.success(200,messageService.findMessageByAdmin(userId));
        }
    }
}
