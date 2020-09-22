package controller;

import bean.JieShu;
import bean.User;
import dao.BookDao;
import dao.daoImpl.BookDaoImpl;
import service.BookService;
import service.serviceImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "BorrowServlet",urlPatterns = "/BorrowServlet")
public class BorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String callnumber = req.getParameter("callnumber");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        JieShu jieShu=new JieShu();
        jieShu.setCallnumber(callnumber);
        jieShu.setUsername(user.getUsername());

        BookService bookService=new BookServiceImpl();
        PrintWriter writer = resp.getWriter();
        try {
            boolean b = bookService.borrowBook(jieShu);
            if (b){
                writer.write("借书成功！");
            }else writer.write("借书失败，您已借阅同一书目图书！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
