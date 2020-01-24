/**
 * Create by Tony
 * 2020.1.23 20:02
 * 配置工具业务 Mapper
 */
package com.tony.tonna.mapper;

import com.tony.tonna.VO.ConstTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UtilMapper {

    /**
     * 获取配置类相应类型全部数据
     * @param constType
     * @return
     */
    List<ConstTypeVO> findConstType(@Param("constType")String constType);
}
