package com.maki.service;

import com.maki.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * 标签
 * */
public interface TagService {
    //分页显示所有标签
    Page<Tag> listTag(Pageable pageable);

    //增加一个标签
    Tag saveTag(Tag tag);

    //获取一个标签
    Tag getTag(Long id);

    //更新一个标签
    Tag updateTag(Long id, Tag tag);

    //删除一个标签
    void deleteTag(Long id);

    //通过标签名查找一个标签
    Tag getTagByTagName(String name);

    //获取所有标签
    List<Tag> listTag();

    //根据ids获取所有标签
    List<Tag> listTag(String ids);

    //获取size个标签
    List<Tag> listTagTop(Integer size);
}
