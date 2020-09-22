package com.maki.dao;

import com.maki.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {
    //根据博客id查询该博客的所有顶级comment（没有子评论的）
    // 根据评论对象的创建时间排序
    List<Comment> findByBlogIdAndParentCommentNull(Long id, Sort sort);
}
