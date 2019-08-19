package com.welfare.controller;

import com.welfare.entity.UserEntity;
import com.welfare.service.UserService;
import com.welfare.util.MD5Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/11 14:14
 * @Description:
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpSession session, String username, String password) {

        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(username)) {
            jsonObject.put("code", "用户名不能为空");
            return jsonObject.toString();
        }
        if (StringUtils.isEmpty(password)) {
            jsonObject.put("code", "密码不能为空");
            return jsonObject.toString();
        }
        UserEntity entity = userService.login(username, password);
        if (StringUtils.isEmpty(entity)) {
            jsonObject.put("code", "用户不存在");
            return jsonObject.toString();
        }
        if (!MD5Util.verfityMd5(password, entity.getPassword())) {
            jsonObject.put("code", "密码不正确");
            return jsonObject.toString();
        }
        session.setAttribute("user", entity);
        if (entity.getRole().equalsIgnoreCase("2")) {
            jsonObject.put("code", "ADMIN");
        }
        jsonObject.put("code", "SUCCESS");
        return jsonObject.toString();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(String name, String password, String phone) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(name)) {
            jsonObject.put("code", "用户名不能为空");
            return jsonObject.toString();
        }
        UserEntity entity = userService.queryOne(name);
        if (!StringUtils.isEmpty(entity)) {
            jsonObject.put("code", "用户名已存在");
            return jsonObject.toString();
        }
        String result = userService.register(name, password, phone);
        if (result.equalsIgnoreCase("SUCCESS")) {
            jsonObject.put("code", result);
        } else {
            jsonObject.put("code", "error");
        }
        return jsonObject.toString();
    }
}
