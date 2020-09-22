package com.maki.dao;

import com.maki.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * 使用spring data jpa
 * 继承JpaRepository类
 * 说明该dao操作的对象是blog，主键类型为Long
 *
 * JpaSpecificationExecutor该接口可以帮助我们进行组合查询
 * */
public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    //jpa已经自动实现了一些基础的增删改查方法

    //自定义查询，查询被推荐的博客，按更新时间排序
    //查询条件以及排序方式，在pageable中定义
    @Query("select b from Blog b where b.recommend=true ")
    List<Blog> findTop(Pageable pageable);

    //自定义查询，全局搜索的分页查询，根据String字符串
    //查询条件以及排序方式，在pageable中定义
    //注意，该sql语句不会帮我们拼接 %query%
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying
    //自定义更新，每次打开博客详情页，获取博客时，先更新该博客的字段，views+1
    @Query("update Blog b set b.views = b.views+1 where b.id=?1 ")
    int updateViews(Long id);

    //返回所有的年份
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y')")
    List<String> findGroupYears();

    //根据年份查询该年下的所有博客
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y')=?1 ")
    List<Blog> findByYear(String year);
}
