package com.welfare.controller;

import com.welfare.entity.UserEntity;
import com.welfare.util.LoginAccountUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/12 14:50
 * @Description:
 */
@Controller
public class UserController {

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView mav = new ModelAndView("user");
        try {
            UserEntity entity = LoginAccountUtil.getUserEntity();
            if (entity == null) {
                return new ModelAndView("login");
            }
            long userId = entity.getId();
            return mav.addObject("user", entity);
        } catch (Exception e) {
            return new ModelAndView("404");
        }
    }
}
