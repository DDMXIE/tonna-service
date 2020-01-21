package com.tony.tonna.service;

import com.tony.tonna.VO.UserMessageVO;
import com.tony.tonna.entity.Message;
import com.tony.tonna.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Resource
    private MessageMapper messageMapper;

    /**
     * 新增留言
     * @param userId
     * @param message
     */
    public int addMessageByAdmin(String userId,Message message){
        String userMessageId = UUID.randomUUID().toString();
        String messageId = message.getMESSAGE_ID();
        int num = messageMapper.addMessageByAdmin(message);
        messageMapper.addUserMessageByAdmin(userMessageId,messageId,userId);
        return num;
    }

    /**
     * 查询留言数据（可根据用户查询）
     * @return List
     */
    public List<UserMessageVO> findMessageByAdmin(String userId){
        return messageMapper.findMessageByAdmin(userId);
    }

    /**
     * 根据id查询留言数据
     * @return List
     */
    public List<Message> findMessageById(String messageId){
        return messageMapper.findMessageById(messageId);
    }

    /**
     * 根据留言id软删除留言数据
     * @return List
     */
    public int deleteMessageByAdmin(String messageId){
        List list = messageMapper.findMessageById(messageId);
        int outputdata = 0;
        if(list.size() == 1){
            outputdata = messageMapper.deleteMessageByAdmin(messageId);
        }
        return outputdata;
    }
}
