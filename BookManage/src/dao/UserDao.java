package dao;

import bean.User;

import java.sql.SQLException;

public interface UserDao {
    public User findUser(User user) throws SQLException;
    public User insertUser(User user) throws SQLException;
}
