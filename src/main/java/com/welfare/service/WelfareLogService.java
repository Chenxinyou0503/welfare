package com.welfare.service;

import com.welfare.entity.WelfareLogEntity;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 15:01
 * @Description:
 */
public interface WelfareLogService {

    public List<WelfareLogEntity> selectListByWelfareId(String welfareId);

}
