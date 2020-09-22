package com.maki.service.impl;

import com.maki.dao.UserDao;
import com.maki.po.User;
import com.maki.service.UserService;
import com.maki.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String name, String password) {
        User user = userDao.findByUsernameAndPassword(name, MD5Utils.code(password));
        return user;
    }
}
