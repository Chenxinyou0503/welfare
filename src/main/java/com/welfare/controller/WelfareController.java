package com.welfare.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.welfare.entity.UserEntity;
import com.welfare.entity.WelfareEntity;
import com.welfare.entity.WelfareLogEntity;
import com.welfare.entity.vo.PageParam;
import com.welfare.service.WelfareLogService;
import com.welfare.service.WelfareService;
import com.welfare.util.LoginAccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/19 10:29
 * @Description:
 */
@Controller
@RequestMapping("/welfare")
public class WelfareController {
    @Autowired
    private WelfareService welfareService;
    @Autowired
    private WelfareLogService welfareLogService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(WelfareEntity entity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "SUCCESS");
        welfareService.save(entity);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(WelfareEntity entity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "SUCCESS");
        welfareService.update(entity);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/selectByUser", method = RequestMethod.POST)
    public String select(PageParam pageParam) {
        JSONObject jsonObject = new JSONObject();
        UserEntity userEntity = LoginAccountUtil.getUserEntity();
        if (StringUtils.isEmpty(userEntity)) {
            jsonObject.put("code", "error");
            jsonObject.put("msg", "请登录");
        }
        jsonObject.put("code", "SUCCESS");
        PageInfo<WelfareEntity> welfareEntityPageInfo = welfareService.selectListByUser(pageParam.getPageNo(), pageParam.getPageSize(), String.valueOf(userEntity.getId()));
        jsonObject.put("list", welfareEntityPageInfo);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/selectLog", method = RequestMethod.POST)
    public String selectLog(String welfareId) {
        JSONObject jsonObject = new JSONObject();
        UserEntity userEntity = LoginAccountUtil.getUserEntity();
        if (StringUtils.isEmpty(userEntity)) {
            jsonObject.put("code", "error");
            jsonObject.put("msg", "请登录");
        }
        jsonObject.put("code", "SUCCESS");
        List<WelfareLogEntity> welfareLogList = welfareLogService.selectListByWelfareId(welfareId);
        jsonObject.put("list", welfareLogList);
        return jsonObject.toJSONString();
    }
}
