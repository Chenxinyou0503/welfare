package com.welfare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/27 10:28
 * @Description:
 */
@Controller
public class ViewController {

    /**
     * 用户
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/user")
    public String user(Model modelMap) {
        return "user";
    }

    /**
     * 充值中心
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/recharge")
    public String recharge(Model modelMap) {
        return "recharge";
    }

    /**
     * 捐款记录
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/donateLog")
    public String donateLog(Model modelMap) {
        return "donateLog";
    }

    /**
     * 项目详情
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/detail")
    public String detail(Model modelMap) {
        return "detail";
    }
}
