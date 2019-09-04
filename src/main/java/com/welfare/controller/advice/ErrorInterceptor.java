package com.welfare.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/11 17:18
 * @Description:
 */
@Controller
public class ErrorInterceptor implements ErrorController {
    private static Logger logger = LoggerFactory.getLogger(ErrorInterceptor.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 401) {
            return "/error/401";
        } else if (statusCode == 404) {
            return "/error/404";
        } else if (statusCode == 403) {
            return "/error/403";
        } else {
            return "/error/500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
