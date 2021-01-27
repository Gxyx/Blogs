package com.san.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.san.dao.CommentMapper;
import com.san.dao.StatisticMapper;
import com.san.model.domain.Comment;
import com.san.model.domain.Statistic;
import com.san.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/17:25
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private StatisticMapper statisticMapper;

    /**
     * 根据文章id分页查询评论
     * @param aid
     * @param page
     * @param count
     * @return
     */
    @Override
    public PageInfo<Comment> getComments(Integer aid, int page, int count) {
        PageHelper.startPage(page,count);
        List<Comment> commentList = commentMapper.selectCommentWithPage(aid);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(commentList);
        return commentPageInfo;
    }

    /**
     * 用户发表评论
     * @param comment
     */
    @Override
    public void pushComment(Comment comment) {
        commentMapper.pushComment(comment);
        //更新评论数据量
        Statistic statistic = statisticMapper.selectStatisticWithArticleId(comment.getArticleId());
        statistic.setCommentsNum(statistic.getCommentsNum()+1);
        statisticMapper.updateArticleHitsWithId(statistic);
    }
}
