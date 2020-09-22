package com.maki.service;

import com.maki.po.Comment;

import java.util.List;

public interface CommentService {
    //根据博客id查询该博客对应的所有comment
    List<Comment> listCommentByBlogId(Long id);

    //保存一个comment
    Comment saveComment(Comment comment);
}
