package service;

import bean.User;

import java.sql.SQLException;

public interface UserService {
    boolean Login(User user) throws SQLException;
    User Register (User user) throws SQLException;
}
