package com.maki.handler;
/*
* 用来拦截所有的Controller，当出现异常信息时，自定义异常处理器
* */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//对所有的controller进行监控
@ControllerAdvice
public class ControllerExceptionHandler {
    //获取日志对象
    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    //当请求出现异常（Exception级别的）时，执行该方法
    @ExceptionHandler(Exception.class)
    //请求参数自动绑定：为了知道哪一个请求出现了异常，和收集异常信息
    public ModelAndView exceptionHandler(HttpServletRequest req,Exception e) throws Exception {
        //记录日志，使用占位符的用法(控制台输出)
        logger.error("Request URL：{}，Exception：{}   ",req.getRequestURL(),e);

        //若获取到的异常信息，上面有ResponseStatues注解（我们自定义的异常），不进行处理，抛给springboot自动处理
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }

        ModelAndView mv=new ModelAndView();
        mv.addObject("url",req.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
