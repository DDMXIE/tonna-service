package com.tony.tonna.control;

import com.tony.tonna.entity.User;
import com.tony.tonna.service.UserSignService;
import com.tony.tonna.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserSignController {
    @Autowired
    private UserSignService userSignService;

    @PostMapping("/tonna/saveSignUser")
    public int saveSignUser(@RequestBody Map userInfoList){
        User user = new User();
        String uid = UUID.randomUUID().toString();
        user.setUSER_ID(uid);
        user.setUSER_NAME(userInfoList.get("username").toString());
        //将密码加密存储到数据库
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encoderPassword = encoder.encode(userInfoList.get("password").toString());
        user.setUSER_PASSWORD(encoderPassword);
        user.setUSER_ENABLED(true);
        user.setUSER_LOCKED(false);
        user.setUSER_IMG("");
        user.setUSER_INTRODUCE("");
        user.setUSER_TEL("");
        String rid = "2565bhg2-bg9f-4dd6-9f03-b1235e883365"; //这是权限为admin（会员）的ROLE id
        String userRoleId = UUID.randomUUID().toString();
        userSignService.saveUserRole(userRoleId,uid,rid);
        return userSignService.saveSignUser(user);
    }

    /**
     * 用户新增或取消关注
     * @param inputData
     * @return Result
     */
    @PostMapping("/admin/addAttentionByUser")
    public Result addAttentionByUser(@RequestBody Map inputData){
        return Result.success(200,userSignService.addAttentionByUser(inputData));
    }

    /**
     * 用户查看关注情况
     * @param ownerId
     * @param targetId
     * @return
     */
    @GetMapping("/tonna/findUserAttentionById")
    public Result findUserAttentionById(@RequestParam(value = "ownerId", required = false) String ownerId,
                                            @RequestParam(value = "targetId", required = false) String targetId){
        if(ownerId == null || targetId == null){
            return Result.fail();
        }else{
            return Result.success(200,userSignService.findUserAttentionById(ownerId,targetId));
        }
    }

    /**
     * 加载用户主页的关注信息
     * @param authorId
     * @param userId
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/tonna/findUserAndAuthorAttention")
    public Result findUserAndAuthorAttention(@RequestParam(value = "authorId", required = false) String authorId,
                                             @RequestParam(value = "userId", required = false) String userId,
                                             @RequestParam(value = "start", required = false) Integer start,
                                             @RequestParam(value = "end", required = false) Integer end){
        if(authorId == null || userId == null){
            return Result.fail();
        }else{
            return Result.success(200,userSignService.findUserAndAuthorAttention(authorId,userId,start,end));
        }
    }

    /**
     * 用户通过id获取自身信息
     * @param userId
     * @return
     */
    @GetMapping("/admin/findUserInfoById")
    public Result findUserInfoById(@RequestParam(value = "userId", required = false) String userId){
        if(userId == null){
            return Result.fail();
        }else{
            return Result.success(200,userSignService.findUserInfoById(userId));
        }
    }

    /**
     * 用户保存更新用户信息
     * @param userInfo
     * @return
     */
    @PostMapping("/admin/saveUserInfoById")
    public Result saveUserInfoById(@RequestBody Map userInfo){
        if(userInfo == null){
            return Result.fail();
        }else{
            return Result.success(200,userSignService.saveUserInfoById(userInfo));
        }
    }

    /**
     * 用户修改密码
     * @param userInfo
     * @return
     */
    @PostMapping("/admin/saveUserPassById")
    public Result saveUserPassById(@RequestBody Map userInfo){
        if(userInfo == null){
            return Result.fail();
        }else{
            return Result.success(200,userSignService.saveUserPassById(userInfo));
        }
    }

    /**
     * 用户头像修改
     * @param userInfo
     * @return
     */
    @PostMapping("/admin/updateUserAvatarById")
    public Result updateUserAvatarById(@RequestBody Map userInfo){
        if(userInfo == null){
            return Result.fail();
        }else{
            return Result.success(200,userSignService.updateUserAvatarById(userInfo));
        }
    }

    /**
     * 管理员分页获取用户信息
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/super/findUserByPage")
    public Result findUserByPage( @RequestParam(value = "start", required = false) Integer start,
                                  @RequestParam(value = "end", required = false) Integer end){
        return Result.success(200,userSignService.findUserByPage(start,end));
    }

    /**
     * 管理员获取系统化基础信息
     * @return
     */
    @GetMapping("/super/findSystemInfo")
    public Result findSystemInfo(){
        return Result.success(200,userSignService.findSystemInfo());
    }
}
