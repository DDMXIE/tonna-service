package com.tony.tonna.service;

import com.tony.tonna.VO.ArticleCollectVO;
import com.tony.tonna.VO.AttentionVO;
import com.tony.tonna.entity.Role;
import com.tony.tonna.entity.User;
import com.tony.tonna.mapper.ArticleMapper;
import com.tony.tonna.mapper.UserMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserSignService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ArticleMapper articleMapper;

    public int saveSignUser(User user){
        return userMapper.saveSignUser(user);
    }

    public int saveUserRole(String userRoleId,String uid,String rid){
        return userMapper.saveUserRole(userRoleId,uid,rid);
    }

    public List<Role> showAllRoleInfo(){

        return userMapper.showAllRoleInfo();
    }

    /**
     * 方法安全 样例
     * @return String
     */
    @Secured("ROLE_USER")
    public String sayHello(){
        return "Hello Tony, your role is USER!";
    }

    /**
     * 用户添加或取消关注
     * @param inputData
     * @return Map
     */
    public Map addAttentionByUser(Map inputData){
        Map outputdata = new HashMap();
        int num = 0;
        String ownerId = inputData.get("ownerId").toString();
        String targetId = inputData.get("targetId").toString();
        if(inputData.get("isattention").equals("attention")){
            List<AttentionVO> attentionList = userMapper.findUserAttentionById(ownerId,targetId);
            if(attentionList.size()==0){
                String attentionId = UUID.randomUUID().toString();
                Date createDate = new Date();
                String isDelete = "2";
                num =  userMapper.addAttentionByUser(attentionId,ownerId,targetId,createDate,isDelete);
                articleMapper.addActivityByUser(UUID.randomUUID().toString(),ownerId,"3",null,targetId,new Date(),attentionId);
                outputdata.put("tip","您已添加关注成功!");
            }else{
                outputdata.put("tip","关注失败！你已关注");
            }
        }else if(inputData.get("isattention").equals("unattention")) {
            num = userMapper.deleteAttention(ownerId,targetId);
            outputdata.put("tip","您已取消关注！");
        }
        outputdata.put("num",num);
        return outputdata;
    }

    /**
     * 用户查看关注情况
     * @param ownerId
     * @param targetId
     * @return List
     */
    public List<AttentionVO> findUserAttentionById(String ownerId,String targetId){
        return userMapper.findUserAttentionById(ownerId,targetId);
    }
}
