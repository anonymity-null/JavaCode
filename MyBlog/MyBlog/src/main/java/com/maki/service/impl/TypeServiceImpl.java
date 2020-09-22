package com.maki.service.impl;

import com.maki.dao.TypeDao;
import com.maki.myexception.PageNotFoundException;
import com.maki.po.Type;
import com.maki.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    //业务层的方法使用事务进行控制
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        /*
            分页查询效果：需要一个Pageable对象，该对象中说明了：每页最多的记录数、排序方式......
        */
        return typeDao.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        //先判断该分类是否存在
        Type t = typeDao.getOne(id);
        if (t == null) {
            throw new PageNotFoundException("分类更新失败!");
        }
        //若该分类存在，更新该分类  将type的属性值赋值到t里面
        BeanUtils.copyProperties(type, t);
        return typeDao.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteById(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }


    @Override
    public List<Type> listType() {

        return typeDao.findAll();
    }

    /*
     * 查询size个分类，根据每个分类中blogs的数量进行排序
     * */
    @Override
    public List<Type> listTypeTop(Integer size) {
        //设置一个排序：通过type中的blogs的数量进行降序排序
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        //说明，查询size个分类，只查询第一页，以sort方式排序
        Pageable pageable=PageRequest.of(0,size,sort);
        return typeDao.findTop(pageable);
    }
}
