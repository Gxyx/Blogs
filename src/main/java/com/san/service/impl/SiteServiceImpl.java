package com.san.service.impl;

import com.github.pagehelper.PageHelper;
import com.san.dao.ArticleMapper;
import com.san.dao.CommentMapper;
import com.san.dao.StatisticMapper;
import com.san.model.ResponseData.StaticticsBo;
import com.san.model.domain.Article;
import com.san.model.domain.Comment;
import com.san.model.domain.Statistic;
import com.san.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/17:40
 */
@Service
@Transactional
public class SiteServiceImpl implements SiteService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private StatisticMapper statisticMapper;


    /**
     * 最新收到的评论
     * @param count
     * @return
     */
    @Override
    public List<Comment> recentComments(int limit) {

        PageHelper.startPage(1,limit > 10 || limit < 1 ? 10 : limit);
        List<Comment> bypage = commentMapper.selectNewComment();
        return bypage;

    }

    /**
     * 最新发表的文章
     * @param count
     * @return
     */
    @Override
    public List<Article> recentArticles(int limit) {

        PageHelper.startPage(1, limit>10 || limit<1 ? 10:limit);
        List<Article> list = articleMapper.selectArticleWithPage();
        // 封装文章统计数据
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            Statistic statistic = statisticMapper.selectStatisticWithArticleId(article.getId());
            article.setHits(statistic.getHits());
            article.setCommentsNum(statistic.getCommentsNum());
        }
        return list;

    }

    /**
     * 获取后台统计的数据
     * @return
     */
    @Override
    public StaticticsBo getStatistics() {

        StaticticsBo staticticsBo = new StaticticsBo();
        Integer articles = articleMapper.countArticle();
        Integer comments = commentMapper.countComment();
        staticticsBo.setArticles(articles);
        staticticsBo.setComments(comments);
        return staticticsBo;

    }

    /**
     * 更新某个文章的统计数据
     * @param article
     */
    @Override
    public void updateStatistics(Article article) {

        Statistic statistic = statisticMapper.selectStatisticWithArticleId(article.getId());
        statistic.setHits(statistic.getHits()+1);
        statisticMapper.updateArticleHitsWithId(statistic);

    }
}
