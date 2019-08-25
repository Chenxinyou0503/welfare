package com.welfare.service.impl;

import com.welfare.dao.WelfareDao;
import com.welfare.entity.WelfareEntity;
import com.welfare.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 13:32
 * @Description:
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private WelfareDao welfareDao;

    /**
     * 管理员修改项目信息
     */
    @Override
    public void updateWelfare(WelfareEntity entity) {
        welfareDao.updateByPrimaryKeySelective(entity);
    }

    /**
     * 管理员审批项目
     * @param welfareId
     * @param type
     */
    @Override
    public void updateWelfareStatus(String welfareId, String type) {
        welfareDao.updateStatus(welfareId, type);
    }

    /**
     * 结算项目
     * @param welfareId
     */
    @Override
    public void settlement(String welfareId) {
        welfareDao.updateStatus(welfareId, "4");
    }
}
