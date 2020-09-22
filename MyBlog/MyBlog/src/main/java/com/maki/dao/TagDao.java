package com.maki.dao;

import com.maki.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
 * 使用spring data jpa
 * 继承JpaRepository类
 * 说明该dao操作的对象是Tag，主键类型为Long
 * */
public interface TagDao extends JpaRepository<Tag, Long> {
    //jpa已经自动实现了一些基础的增删改查方法

    //根据名称查找一条tag
    Tag findByName(String name);

    //自定义查询，通过tag对象中的blogs的大小进行排序(即每一个分类的博客数量进行排序)
    //排序方式，以及要展示的页数，由pageable决定
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
