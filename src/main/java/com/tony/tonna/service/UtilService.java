/**
 * Create by Tony
 * 2020.1.23 20:02
 * 配置工具业务 Service
 */
package com.tony.tonna.service;

import com.tony.tonna.VO.ConstTypeVO;
import com.tony.tonna.mapper.UtilMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UtilService {

    @Resource
    private UtilMapper utilMapper;

    /**
     * 获取配置类相应类型全部数据
     * @param constType
     * @return
     */
    public List<ConstTypeVO> findConstType(String constType){
        return utilMapper.findConstType(constType);
    }
}
