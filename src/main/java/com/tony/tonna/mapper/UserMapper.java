package com.tony.tonna.mapper;

import com.tony.tonna.VO.AttentionVO;
import com.tony.tonna.VO.UserInfoVO;
import com.tony.tonna.entity.Article;
import com.tony.tonna.entity.Role;
import com.tony.tonna.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    //根据用户名查询用户信息
    User loadUserByUsername(String userName);

    //通过用户ID获取用户角色
    List<Role> getUserRolesByUid(String uid);

    //注册 保存用户
    int saveSignUser(User user);

    //保存用户角色信息
    int saveUserRole(@Param("userRoleId")String userRoleId, @Param("uid")String uid, @Param("rid")String rid);

    //查询用户所有角色信息
    List<Role> showAllRoleInfo();

    //保存用户和token的关系
    int saveUserToken(String userTokenId,String uid,String token);

    //根据用户ID获取token
    String getTokenByUserId(String uid);

    //通过uid删除一条数据
    int deleteUserTokenByUid(String uid);

    /**
     * 用户id获取用户基础信息
     * @param userId
     * @return
     */
    List<UserInfoVO> findUserInfoByUserId(@Param("userId")String userId);

    /**
     * 用户通过id和目标id查看关注情况
     * @param ownerId
     * @param targetId
     * @return
     */
    List<AttentionVO> findUserAttentionById(@Param("ownerId")String ownerId, @Param("targetId")String targetId);

    /**
     * 用户新增关注
     * @param attentionId
     * @param ownerId
     * @param targetId
     * @param createDate
     * @param isDelete
     * @return
     */
    int addAttentionByUser(@Param("attentionId")String attentionId,@Param("ownerId")String ownerId,
                             @Param("targetId")String targetId,@Param("createDate") Date createDate,
                             @Param("isDelete")String isDelete);

    /**
     * 用户根据id和目标id取消关注
     * @param ownerId
     * @param targetId
     * @return
     */
    int deleteAttention(@Param("ownerId")String ownerId,@Param("targetId")String targetId);

    /**
     * 根据关注id获取关注信息
     * @param attentionId
     * @return
     */
    List<AttentionVO> findAttentionByAttentionId(@Param("attentionId")String attentionId);

    /**
     * 根据用户id查询该用户所有的关注
     * @param ownerId
     * @param start
     * @param end
     * @return
     */
    List<AttentionVO> findAttentionByOwnerId(@Param("ownerId")String ownerId,@Param("start")int start,@Param("end")int end);
}
