package com.san.dao;

import com.san.model.domain.Article;
import com.san.model.domain.Statistic;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/14:44
 * 文章统计类
 */
@Mapper
public interface StatisticMapper {
    /**
     * 新增文章的对应统计
     * @param article
     */
    @Insert("INSERT INTO t_statistic(article_id,hits,comments_num) values (#{id},0,0)")
     public void addStatistic(Article article);

    /**
     * 根据文章id查询点击量和评论相关信息
      * @param articleId
     * @return
     */
    @Select("SELECT * FROM t_statistic WHERE article_id=#{articleId}")
    public Statistic selectStatisticWithArticleId(Integer articleId);


    /**
     * 通过文章id更新点击量
     * @param statistic
     */
    @Update("UPDATE t_statistic SET hits=#{hits} " +
            "WHERE article_id=#{articleId}")
    public void updateArticleHitsWithId(Statistic statistic);


    /**
     * 通过文章id更新评论量
     * @param statistic
     */
    @Update("UPDATE t_statistic SET comments_num=#{commentsNum}"+"WHERE article_id=#{articleId}")
    public void updateArticleCommentsWithId(Statistic statistic);


    /**
     * 根据id删除统计数据
     * @param aid
     */
    @Delete("DELETE FROM t_statistic WHERE article_id=#{aid}")
    public void deleteStatisticWithId(int aid);

    /**
     * 统计文章热度
     * @return
     */
    @Select("SELECT * FROM t_statistic WHERE hits !='0'"+"ORDER BY hits DESC, comments_num DESC")
    public List<Statistic> getStatistic();

    /**
     * 统计博客文章总访问量
     * @return
     */
    @Select("SELECT SUM(hits) FROM t_statistic")
    public long getTotalVisit();

    /**
     * 统计文章总评论量
     * @return
     */
    @Select("SELECT SUM(comments_num) FROM t_statistic")
    public long getTotalComment();
}

