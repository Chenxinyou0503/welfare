package com.welfare.controller;

import com.github.pagehelper.PageInfo;
import com.welfare.entity.WelfareEntity;
import com.welfare.entity.vo.PageParam;
import com.welfare.service.WelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private WelfareService welfareService;

    @RequestMapping("/")
    public String index(Model modelMap, PageParam pageParam) {
        List<WelfareEntity> list = new ArrayList<>();
        PageInfo<WelfareEntity> resultList = welfareService.selectListByIndex(pageParam.getPageNo(), pageParam.getPageSize());
        WelfareEntity entity = new WelfareEntity();
        entity.setWelfareAccount(1);
        entity.setCreateTime(new Date());
        entity.setWelfareTitle("爱心互助平台-我想活下去");
        entity.setId(123);
        entity.setTag("大病，癌症");
        list.add(entity);
        modelMap.addAttribute("entity", resultList);
        return "index";
    }
}
