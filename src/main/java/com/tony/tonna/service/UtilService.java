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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * html中获取网页中图片的地址
     */
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    /**
     * html中获取纯文本
     * @param html
     * @return
     */
    public String removeHtml(String html) {
        String content = "";
        content = html.replaceAll( ".*?<body.*?>(.*?)<\\/body>", "$1");
        content=content.replaceAll("</?[a-zA-Z]+[^><]*>","");
        content.replaceAll("\n","");
        return content;
    }
}
