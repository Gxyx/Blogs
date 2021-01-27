package com.san.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.san.model.domain.Comment;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/16:29
 */
public interface CommentService {

    /**
     * 获取文章下的评论
     * @param count
     * @return
     */

    public PageInfo<Comment> getComments(Integer aid, int page, int count);


    /**
     * 用户发表评论
     * @param comment
     */
    public void pushComment(Comment comment);
}
