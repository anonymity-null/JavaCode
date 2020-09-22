package service.serviceImpl;

import bean.User;
import dao.UserDao;
import dao.daoImpl.UserDaoImpl;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao userDao;
    @Override
    public boolean Login(User user) throws SQLException {
        userDao=new UserDaoImpl();
        User findByUser = userDao.findUser(user);
        return findByUser != null;

    }

    @Override
    public User Register(User user) throws SQLException {
        userDao=new UserDaoImpl();
        return userDao.insertUser(user);
    }
}
