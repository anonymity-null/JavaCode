package controller;

import bean.Reserve;
import bean.User;
import dao.ReserveDao;
import dao.daoImpl.ReserveDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ReserveServlet",urlPatterns = "/ReserveServlet")
public class ReserveServlet extends HttpServlet {
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
        User user = (User) req.getSession().getAttribute("user");

        Reserve reserve=new Reserve();
        reserve.setUsername(user.getUsername());
        reserve.setReservenumber(callnumber);

        ReserveDao reserveDao=new ReserveDaoImpl();
        PrintWriter writer = resp.getWriter();
        try {
            Reserve isReserve = reserveDao.insertReserve(reserve);
            if (isReserve!=null){
                writer.write("预约成功！");
            }else writer.write("预约失败！");
            writer.flush();
            writer.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
