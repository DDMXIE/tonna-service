package com.tony.tonna.VO;

public class AttentionLoadVO {
    private AttentionVO attentionVO;
    private UserInfoVO userInfoVO;
    private String isMyAttention;

    public AttentionVO getAttentionVO() {
        return attentionVO;
    }

    public void setAttentionVO(AttentionVO attentionVO) {
        this.attentionVO = attentionVO;
    }

    public UserInfoVO getUserInfoVO() {
        return userInfoVO;
    }

    public void setUserInfoVO(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }

    public String getIsMyAttention() {
        return isMyAttention;
    }

    public void setIsMyAttention(String isMyAttention) {
        this.isMyAttention = isMyAttention;
    }
}
