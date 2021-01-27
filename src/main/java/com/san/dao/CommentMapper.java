package com.san.dao;

import com.san.model.domain.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/16:15
 */
@Mapper
public interface CommentMapper {

    /**
     * 分页展示某个博客的评论
     * @param aid
     * @return
     */
    @Select("SELECT * FROM t_comment WHERE article_id=#{aid} ORDER BY id DESC")
    public List<Comment> selectCommentWithPage(Integer aid);

    /**
     * 查询最新几条评论
     * @return
     */
    @Select("SELECT * FROM t_comment ORDER BY id DESC")
    public List<Comment> selectNewComment();

    /**
     * 发表评论
     * @param comment
     */
    @Insert("INSERT INTO t_comment (article_id,created,author,ip,content)" + " VALUES (#{articleId}, #{created},#{author},#{ip},#{content})")
    public void pushComment(Comment comment);

    /**
     * 统计评论数
     * @return
     */
    @Select("SELECT COUNT(1) FROM t_comment")
    public Integer countComment();

    /**
     * 通过文章ID删除评论信息
     * @param aid
     */
    @Delete("DELETE FROM t_comment WHERE article_id=#{aid}")
    public void deleteCommentWithId(Integer aid);

}
