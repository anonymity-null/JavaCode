package com.maki.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
* 自定义一个拦截器：用来拦截未登录的url
* */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user")==null){
            request.setAttribute("message","请先登录!!!");
            request.getRequestDispatcher("/admin").forward(request,response);
            return false;
        }
        return true;
    }
}
