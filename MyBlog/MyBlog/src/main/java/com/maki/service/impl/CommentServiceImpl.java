package com.maki.service.impl;

import com.maki.dao.CommentDao;
import com.maki.po.Comment;
import com.maki.service.CommentService;
import com.maki.util.CommentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Transactional
    @Override
    public List<Comment> listCommentByBlogId(Long id) {
        //根据comment的创建时间降序排序
        Sort sort = Sort.by("createTime");

        //获取所有没有子评论的评论对象
        List<Comment> comments = commentDao.findByBlogIdAndParentCommentNull(id, sort);
        //只显示两级评论，一级为顶评论，二级为顶评论下的所有子评论
        CommentUtils commentUtils = new CommentUtils();
        List<Comment> first_secondaryComment = commentUtils.eachComment(comments);
        return first_secondaryComment;
    }

    /*
     * 保存一个comment对象，需要先判断该对象有无 父评论
     * 前端传来的值为-1时，表示该comment没有父评论
     * 不为-1时，保存该comment时，说明该comment的父评论对象
     * */
    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        //获取从前端页面传来的父评论id
        Long parentCommentId = comment.getParentComment().getId();

        //该comment对象有父评论时
        if (parentCommentId != -1) {
            //保存该comment时，先说明该comment的父评论对象
            comment.setParentComment(commentDao.getOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentDao.save(comment);
    }
}
