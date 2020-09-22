package com.maki.service;

import com.maki.po.Blog;
import com.maki.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    //获取一个博客
    Blog getBlog(Long id);

    //分页查询 + 条件查询(搜索框中的属性 title、typeID、recommend)   封装为一个blog对象
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    //新增一个博客
    Blog saveBlog(Blog blog);

    //更新一个博客
    Blog updateBlog(Long id, Blog blog);

    //删除一个博客
    void deleteBlog(Long id);

    //分页查询
    Page<Blog> listBlog(Pageable pageable);

    //查询size个被推荐的博客，以更新时间排序
    List<Blog> listRecommendBlogTop(Integer size);

    //全局搜索的分页查询，根据指定的条件（String字符串）
    Page<Blog> listBlog(String query,Pageable pageable);

    //查询一个博客，将博客的content内容（Markdown格式）转为HTML
    Blog getBlogConvertContent(Long id);

    //查询所有符合某一标签属性的博客，分页的形式
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    //按照时间线，返回每个时间线的所有博客
    Map<String ,List<Blog>> archiveBlog();

    //返回博客的总条数
    Long countBlog();
}
