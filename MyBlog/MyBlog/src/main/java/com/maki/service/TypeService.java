package com.maki.service;

import com.maki.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * 分类
 * */
public interface TypeService {
    //新增一个分类
    Type saveType(Type type);

    //获取一个分类
    Type getType(Long id);

    //分页查询获取所有分类
    Page<Type> listType(Pageable pageable);

    //更新一个分类
    Type updateType(Long id, Type type);

    //删除一个分类
    void deleteType(Long id);

    //根据名称查找分类
    Type getTypeByName(String name);

    //获取所有分类
    List<Type> listType();

    //获取size个分类
    List<Type> listTypeTop(Integer size);
}
