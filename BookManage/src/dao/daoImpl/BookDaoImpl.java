package dao.daoImpl;

import bean.Book;
import dao.BookDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements BookDao {
    QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public List<Book> findAllBook() throws SQLException {
        String sql="select * from book";
        return runner.query(sql,new BeanListHandler<Book>(Book.class));
    }

    @Override
    public int borrowBook(String callnumber) throws SQLException {
        String sql="update book set num=num-1 where callnumber="+callnumber;
        return runner.update(sql);
    }
}
