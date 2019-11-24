package com.tony.tonna.mapper;

import com.tony.tonna.entity.Role;
import com.tony.tonna.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
