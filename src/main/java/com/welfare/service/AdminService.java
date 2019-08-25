package com.welfare.service;

import com.welfare.entity.WelfareEntity;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 13:28
 * @Description:
 */
public interface AdminService {

    /**
     * 编辑筹款信息
     *
     * @param entity
     */
    public void updateWelfare(WelfareEntity entity);

    /**
     * 审核项目
     *
     * @param welfareId
     * @param type
     */
    public void updateWelfareStatus(String welfareId, String type);

    /**
     * 结算项目
     *
     * @param welfareId
     */
    public void settlement(String welfareId);
}
