package com.welfare.service.impl;

import com.github.pagehelper.PageInfo;
import com.welfare.entity.WelfareEntity;
import com.welfare.service.WelfareService;
import com.welfare.util.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 14:59
 * @Description:
 */

public class WelfareServiceImplTest extends WelfareTest {

    @Autowired
    private WelfareService welfareService;

    @Test
    public void save() {
        WelfareEntity entity = new WelfareEntity();
        entity.setTag("大病医疗");
        entity.setWelfareAccount(10000);
        welfareService.save(entity);
    }

    @Test
    public void update() {
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void selectListByAdmin() {
        PageInfo pageInfo = welfareService.selectListByAdmin(1, 10, "");
        System.out.println(JsonUtils.convertObjectToString(pageInfo));
    }

    @Test
    public void selectListByUser() {
    }

    @Test
    public void selectListByIndex() {
    }
}