package com.san.service;

import com.github.pagehelper.PageInfo;
import com.san.model.domain.Article;

import java.util.List;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/15:30
 *
 */
public interface ArticleService {

    /**
     * 分页查询文章列表
     * @param page
     * @param count
     * @return
     */
    public PageInfo<Article> selectArticleWithPage(Integer page,Integer count);


    /**
     * 统计文章排名前时热度
     * @return
     */
    public List<Article> getHeatArticles();

    /**
     * 根据文章id查询单个文章信息
     * @param id
     * @return
     */
    public Article selectArticleWithId(Integer id);


    /**
     * 发布文章
     * @param article
     */
    public void publish(Article article);

    /**
     * 根据主键更新文章
     * @param article
     */
    public void updateArticleWithId(Article article);

    /**
     * 根据主键删除文章
     * @param id
     */
    void deleteArticleWithId(int id);
}
