package com.tony.tonna.mapper;

import com.tony.tonna.VO.UserMessageVO;
import com.tony.tonna.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    /**
     * MESSAGE表中新增留言数据
     * @param message
     * @return List
     */
    int addMessageByAdmin(Message message);

    /**
     * USER_MESSAGE中新增留言数据
     * @param userMessageId
     * @param messageId
     * @param userId
     * @return int
     */
    int addUserMessageByAdmin(@Param("USER_MESSAGE_ID")String userMessageId,
                              @Param("MESSAGE_ID")String messageId,
                              @Param("USER_ID")String userId);

    /**
     * 查询留言数据(可根据userId用户查询)
     * @return List
     */
    List<UserMessageVO> findMessageByAdmin(@Param("userId")String userId);

    /**
     * 根据id查询留言数据
     * @return List
     */
    List<Message> findMessageById(@Param("messageId")String messageId);

    /**
     * 根据留言id软删除留言数据
     * @return int
     */
    int deleteMessageByAdmin(@Param("messageId")String messageId);
}
