package dao;

import bean.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    List<Book> findAllBook() throws SQLException;
    int borrowBook(String callnumber) throws SQLException;

}
