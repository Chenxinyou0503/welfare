package com.welfare.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.welfare.entity.UserEntity;
import com.welfare.entity.WelfareEntity;
import com.welfare.entity.vo.PageParam;
import com.welfare.service.WelfareLogService;
import com.welfare.service.WelfareService;
import com.welfare.util.LoginAccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/9/1 12:43
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private WelfareService welfareService;
    @Autowired
    private WelfareLogService welfareLogService;


    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    public String select(PageParam pageParam, String type) {
        JSONObject jsonObject = new JSONObject();
        UserEntity userEntity = LoginAccountUtil.getUserEntity();
        if (StringUtils.isEmpty(userEntity)) {
            jsonObject.put("code", "error");
            jsonObject.put("msg", "请登录");
        }
        jsonObject.put("code", "SUCCESS");
        PageInfo<WelfareEntity> welfareEntityPageInfo = welfareService.selectListByAdmin(pageParam.getPageNo(), pageParam.getPageSize(), type);
        jsonObject.put("list", welfareEntityPageInfo);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(WelfareEntity entity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "SUCCESS");
        welfareService.update(entity);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/settlement", method = RequestMethod.POST)
    public String settlement(String welfareId) {
        JSONObject jsonObject = welfareService.settlement(welfareId);
        return jsonObject.toJSONString();
    }
}
