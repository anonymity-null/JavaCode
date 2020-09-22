package com.maki.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/*
 * 这是一个切面类，用来记录日志
 * */
@Component
@Aspect
public class LogAspect {
    //获取日志对象
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //设置切点
    @Pointcut("execution(* com.maki.web.controller.*.*(..))")
    public void log() {
    }

    //前置通知（在切入点方法执行前，记录一下：请求的url、ip、切入点的方法名、切入点的方法参数）
    @Before("log()")
    //使用连接点类(JoinPoint)，可以获取到切入点的方法名，类名、参数等等属性
    public void doBefore(JoinPoint joinPoint) {
        //获取请求对象HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();

        //获取url
        String url = req.getRequestURL().toString();
        //获取ip
        String ip = req.getRemoteAddr();
        //获取切入点方法名(包名+类名+方法名)
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //获取切入点方法的参数
        Object[] args = joinPoint.getArgs();
        //封装成对象
        RequestLog reqlog = new RequestLog(url, ip, classMethod, args);

        //打印日志信息
        logger.info("Request：{}",reqlog);
    }

    //最终通知（该通知先于后置通知执行）
    @After("log()")
    public void doAfter() {
       /* logger.info("-----------------doAfter----------------");*/
    }

    //后置通知，同时获取到切点方法执行后的返回值
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturning(Object result) {
        //使用占位符的方式，打印日志信息
        logger.info("Result：{}", result);
    }

    //匿名内部类(封装要记录的日志信息)
    private class RequestLog {
        //请求的url
        private String url;
        //请求的IP
        private String ip;
        //切入点方法名称
        private String classMethod;
        //切入点方法的参数
        private Object[] args;

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }
    }
}
