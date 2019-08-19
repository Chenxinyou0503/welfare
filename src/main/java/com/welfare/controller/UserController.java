package com.welfare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/12 14:50
 * @Description:
 */
@Controller
public class UserController {

    @RequestMapping("/user")
    public String user() {
        return "user";
    }
}
