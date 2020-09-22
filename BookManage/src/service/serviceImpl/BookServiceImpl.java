package service.serviceImpl;

import bean.JieShu;
import dao.BookDao;
import dao.JieShuDao;
import dao.daoImpl.BookDaoImpl;
import dao.daoImpl.JieShuDaoImpl;
import service.BookService;

import java.sql.SQLException;

public class BookServiceImpl implements BookService {

    @Override
    public boolean borrowBook(JieShu jieShu) throws SQLException {
        JieShuDao jieShuDao=new JieShuDaoImpl();
        JieShu callnumberByJieShu = jieShuDao.findCallnumberByJieShu(jieShu);
        if (callnumberByJieShu!=null){
            return false;
        }else {

            jieShuDao.insertItem(jieShu);
            BookDao bookDao=new BookDaoImpl();
            bookDao.borrowBook(jieShu.getCallnumber());
            return true;
        }

    }
}
