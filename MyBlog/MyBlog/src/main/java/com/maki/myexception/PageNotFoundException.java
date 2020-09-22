package com.maki.myexception;

/*
* 自定义一个异常，用来表示页面404
* */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//设置响应状态码(404)，将该异常转为404状态
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends RuntimeException {

    public PageNotFoundException() {
    }

    public PageNotFoundException(String message) {
        super(message);
    }

    public PageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
