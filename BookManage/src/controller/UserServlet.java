package controller;

import bean.User;
import service.UserService;
import service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        HttpSession session = req.getSession();

        UserService userService = new UserServiceImpl();
        try {
            if (userService.Login(user)) {
                session.setAttribute("user", user);
                resp.sendRedirect("index.html");
            } else {
                PrintWriter writer = resp.getWriter();
                writer.write("账号或密码错误，登陆失败！");
                writer.flush();
                writer.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
