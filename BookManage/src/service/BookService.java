package service;

import bean.JieShu;

import java.sql.SQLException;

public interface BookService {
    boolean borrowBook(JieShu jieShu) throws SQLException;
}
