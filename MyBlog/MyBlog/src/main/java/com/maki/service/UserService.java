package com.maki.service;

import com.maki.po.User;

public interface UserService {
    //检测用户是否存在
    public User checkUser(String name,String password);
}
