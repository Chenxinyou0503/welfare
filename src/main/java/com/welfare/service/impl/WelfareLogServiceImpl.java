package com.welfare.service.impl;

import com.welfare.dao.WelfareLogDao;
import com.welfare.entity.WelfareLogEntity;
import com.welfare.service.WelfareLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 13:33
 * @Description:
 */
@Service
public class WelfareLogServiceImpl implements WelfareLogService {
    @Autowired
    private WelfareLogDao welfareLogDao;

    @Override
    public List<WelfareLogEntity> selectListByWelfareId(String welfareId) {
        return welfareLogDao.selectListByWelfareId(welfareId);
    }
}
