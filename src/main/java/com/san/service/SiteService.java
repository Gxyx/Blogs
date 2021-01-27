package com.san.service;

import com.san.model.ResponseData.StaticticsBo;
import com.san.model.domain.Article;
import com.san.model.domain.Comment;

import java.util.List;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/17:26
 * 博客站点
 */
public interface SiteService {

    /**
     * 最新收到的评论
     * @param count
     * @return
     */
    public List<Comment> recentComments(int count);

    /**
     * 最新发表的文章
     * @param count
     * @return
     */
    public List<Article> recentArticles(int count);

    /**
     * 获取后台统计的数据
     * @return
     */
    public StaticticsBo getStatistics();

    /**
     * 更新某个文章的统计数据
     * @param article
     */
    public void updateStatistics(Article article);
}
