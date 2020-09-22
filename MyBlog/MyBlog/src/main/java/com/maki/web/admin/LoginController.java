package com.maki.web.admin;

import com.maki.po.User;
import com.maki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /*
     * 去往登录页
     * */
    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    /*
     * 获取登录页提交的表单，进行判断用户是否存在
     * 若不存在，重定向到登录页（可以使用RedirectAttributes对象来传递参数）
     * */
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);             //为了不把密码传入session中
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            //重定向的页面，可以使用RedirectAttributes对象来传递参数
            attributes.addFlashAttribute("message", "用户名或者密码错误!!!");
            // 重定向时，因为当前的url路径已经在admin下
            // 所以使用‘/’退回到项目的根路径，再发admin请求，否则发出的url请求是：admin/admin
            return "redirect:/admin";
        }
    }

    /*
     * 用于注销账号
     * */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
