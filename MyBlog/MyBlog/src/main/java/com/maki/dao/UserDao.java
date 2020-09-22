package com.maki.dao;

import com.maki.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
/*
 * 使用spring data jpa
 * 继承JpaRepository类
 * 说明该dao操作的对象是User，主键类型为Long
 * */

public interface UserDao extends JpaRepository<User,Long> {
    //遵循jpa的命名规则，自动实现该方法
    User findByUsernameAndPassword(String username,String password);

}
