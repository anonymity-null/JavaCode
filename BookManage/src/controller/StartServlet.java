package controller;

import bean.Book;
import com.google.gson.Gson;
import dao.BookDao;
import dao.UserDao;
import dao.daoImpl.BookDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StartServlet",urlPatterns = "/StartServlet")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        BookDao bookDao=new BookDaoImpl();
        try {
            List<Book> allBook = bookDao.findAllBook();
            Gson gson=new Gson();
            String json = gson.toJson(allBook);
            System.out.println(json);
            PrintWriter writer = resp.getWriter();
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
