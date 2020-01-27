/**
 * Create by Tony
 * 2020.1.23 19:51
 * 文章笔记业务 Serive
 */
package com.tony.tonna.service;

import com.tony.tonna.VO.ArticleFindAllVO;
import com.tony.tonna.entity.Article;
import com.tony.tonna.entity.User;
import com.tony.tonna.mapper.ArticleMapper;
import com.tony.tonna.mapper.UtilMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UtilService utilService;

    /**
     * 新增修改笔记
     * @param article
     * @param flag
     * @return Map
     */
    public Map addUpdateArticle(Article article,String flag){
        Map outputdata = new HashMap();
        if(flag.equals("update")){
            articleMapper.removeArticleById(article.getARTICLE_ID());
        }
        int num = articleMapper.addUpdateArticle(article);
        if(num == 1){
            if(flag.equals("add")){
                outputdata.put("tip","您已成功添加笔记!");
            }else if(flag.equals("update")){
                outputdata.put("tip","您已成功修改笔记!");
            }
        }
        outputdata.put("num",num);
        outputdata.put("id",article.getARTICLE_ID());
        outputdata.put("createDate",article.getCREATE_DATE());
        outputdata.put("articleStatus",article.getARTICLE_STATUS());
        outputdata.put("isDelete",article.getIS_DELETE());
        return outputdata;
    }

    /**
     * 根据笔记id查询笔记详情
     * @param articleId
     * @return List
     */
    public List<Article> findArticleById(String articleId){
        return articleMapper.findArticleById(articleId);
    }

    /**
     * 发布笔记
     * @param articleId
     * @return
     */
    public Map publishedAriticle(String articleId){
        Map outputData = new HashMap();
        articleMapper.updateArticleStatus(articleId,"1");
        List list = articleMapper.findArticleById(articleId);
        outputData.put("data",list);
        return outputData;
    }

    /**
     * 获取所有笔记信息
     * @return
     */
    public List<ArticleFindAllVO> findAllArticle(String typeId,int start,int end){
//        List<Article> list = articleMapper.findArticleById("");
        List<Article> list = articleMapper.findAllArticle(typeId,start,end);
        List<ArticleFindAllVO> newlist = new ArrayList<>();
        for(Article article : list){
            ArticleFindAllVO articleFindAllVO = new ArticleFindAllVO();
            articleFindAllVO.setARTICLE(article);
            articleFindAllVO.setIMG_URL(utilService.getImgStr(article.getARTICLE_CONTENT_HTML()));
            articleFindAllVO.setARTICLE_INTRODUCE(utilService.removeHtml(article.getARTICLE_CONTENT_HTML()));
            List userlist = articleMapper.findAuthorById(article.getARTICLE_ORIGIN_USER_ID());
            User user = (User) userlist.get(0);
            articleFindAllVO.setARTICLE_AUTHOR(user.getUSER_NAME());
            newlist.add(articleFindAllVO);
        }
        return newlist;
    }
}
