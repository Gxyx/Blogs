package com.san.dao;

import com.san.model.domain.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/14:43
 * 文章详情类
 */
@Mapper
public interface ArticleMapper {

    /**
     * 根据id查询文章信息
     * @param id
     * @return
     */
    @Select("select * from t_article where id=#{id}")
    public Article selectArticleWithId(Integer id);

    /**
     * 发表文章
     * @param article
     * @return
     */
    @Insert("INSERT INTO t_article (title,created,modified,tags,categories," + " allow_comment, thumbnail, content)" + " VALUES (#{title},#{created}, #{modified}, #{tags}, #{categories}," + " #{allowComment}, #{thumbnail}, #{content})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public Integer publishArticle(Article article);

    /**
     * 文章分页查询
     * @return
     */
    @Select("SELECT * FROM t_article ORDER BY id DESC")
    public List<Article> selectArticleWithPage();


    /**
     * 根据ID删除文章
     * @param id
     * @return
     */
    @Delete("DELETE FROM t_article WHERE id=#{id}")
    public Integer deleteArticleWithId(int id);

    /**
     * 统计文章
     * @return
     */
    @Select("SELECT COUNT(1) FROM t_article")
    public Integer countArticle();

    /**
     * 根据id更新文章
     * @param article
     * @return
     */
    public Integer updateArticleWithId(Article article);
}
