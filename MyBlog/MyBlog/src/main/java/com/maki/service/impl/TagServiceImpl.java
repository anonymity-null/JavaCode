package com.maki.service.impl;

import com.maki.dao.TagDao;
import com.maki.myexception.PageNotFoundException;
import com.maki.po.Tag;
import com.maki.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    /*
        分页查询效果：需要一个Pageable对象，该对象中说明了：每页最多的记录数、排序方式......
    */
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        Page<Tag> page = tagDao.findAll(pageable);
        return page;
    }

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        Tag t = tagDao.save(tag);
        return t;
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagDao.getOne(id);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        //先判断该分类是否存在
        Tag t = tagDao.getOne(id);
        if (t == null) {
            throw new PageNotFoundException("分类更新失败!");
        }
        //若该分类存在，更新该分类  将type的属性值赋值到t里面
        BeanUtils.copyProperties(tag, t);
        return tagDao.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.deleteById(id);
    }

    @Transactional
    @Override
    public Tag getTagByTagName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {      //ids  1,2,3
        //可以传入一个list集合，来根据ids获取所有标签
        return tagDao.findAllById(convertToList(ids));
    }

    /*
    * 获取size个标签，通过标签的博客数量进行降序排序
    * */
    @Override
    public List<Tag> listTagTop(Integer size) {
        //设置一个排序：通过tag中的blogs的数量进行降序排序
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        //说明，查询size个分类，只查询第一页，以sort方式排序
        Pageable pageable= PageRequest.of(0,size,sort);
        return tagDao.findTop(pageable);
    }

    //将字符串ids转成id的集合(将ids，转为id，存入list集合)
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(Long.parseLong(idarray[i]));
            }
        }
        return list;
    }
}
