package com.tony.tonna.service;

import com.tony.tonna.VO.ArticleCollectVO;
import com.tony.tonna.VO.AttentionLoadVO;
import com.tony.tonna.VO.AttentionVO;
import com.tony.tonna.VO.UserVO;
import com.tony.tonna.entity.Role;
import com.tony.tonna.entity.User;
import com.tony.tonna.mapper.ArticleMapper;
import com.tony.tonna.mapper.TalkMapper;
import com.tony.tonna.mapper.UserMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Resource
    private TalkMapper talkMapper;

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

    /**
     * 加载用户主页的关注信息
     * @param authorId
     * @param userId
     * @param start
     * @param end
     * @return
     */
    public List findUserAndAuthorAttention(String authorId,String userId,int start,int end){
        //先找到auhtor所有关注的人
        List<AttentionVO> authorAttentionList = userMapper.findAttentionByOwnerId(authorId,start,end);
        List<AttentionLoadVO> attentionLoadList = new ArrayList<>();
        for (AttentionVO attention:authorAttentionList) {
            AttentionLoadVO attentionLoadVO = new AttentionLoadVO();
            attentionLoadVO.setAttentionVO(attention);
            attentionLoadVO.setUserInfoVO(userMapper.findUserInfoByUserId(attention.getTARGET_ID()).get(0));
            List myAttention = userMapper.findUserAttentionById(userId,attention.getTARGET_ID());
            if(myAttention.size()!=0){
                attentionLoadVO.setIsMyAttention("1");
            }else{
                attentionLoadVO.setIsMyAttention("0");
            }
            attentionLoadList.add(attentionLoadVO);
        }
        return attentionLoadList;
    }

    /**
     * 管理员分页获取用户信息
     * @param start
     * @param end
     * @return
     */
    public Map findUserByPage(int start,int end){
        Map outputData = new HashMap();
        List<UserVO> userList = userMapper.findAllUser();
        for (UserVO user:userList
             ) {
            List<Role> roleList = userMapper.findRoleByRoleId(userMapper.findRoleIdByUserId(user.getUSER_ID()));
            user.setROLE_ID(roleList.get(0).getROLE_ID());
            user.setROLE_NAME(roleList.get(0).getROLE_NAME());
            user.setROLE_NAME_DCSP(roleList.get(0).getROLE_NAME_DSCP());
        }
        int firstIndex = (start - 1) * end;
        int lastIndex = start * end;
        if(lastIndex > userList.size()){
            lastIndex = firstIndex+(lastIndex - userList.size());
        }
        outputData.put("total",userList.size());
        outputData.put("userList",userList.subList(firstIndex,lastIndex));
        return outputData;
    }

    /**
     * 管理员获取系统化基础信息
     * @return
     */
    public Map findSystemInfo(){
        Map outputdata = new HashMap();
        outputdata.put("userNum",userMapper.countUserNum());
        outputdata.put("articleNum",articleMapper.countArticleNum());
        outputdata.put("collectNum",articleMapper.countCollectNum());
        outputdata.put("talkNum",talkMapper.countTalkNum());
        return outputdata;
    }
}
