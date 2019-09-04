package com.welfare.controller;

import com.welfare.entity.WelfareEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Model modelMap) {
        List<WelfareEntity> list = new ArrayList<>();
        WelfareEntity entity = new WelfareEntity();
        entity.setWelfareAccount(1);
        entity.setCreateTime(new Date());
        entity.setWelfareTitle("爱心互助平台-我想活下去");
        entity.setId(123);
        entity.setTag("大病，癌症");
        list.add(entity);
        modelMap.addAttribute("entity",list);
        return "index";
    }
}
