package com.welfare.service;

import com.welfare.entity.UserAccountEntity;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 15:00
 * @Description: 用户账户功能，充值，提现 交易查询
 */
public interface UserAccountService {
    /**
     * 充值功能
     *  假充值-记录充值记录
     * @param userId 用户ID
     * @param amount 金额
     */
    public void recharge(String userId, int amount);

    /**
     * 提现
     *  假提现-记录提现记录
     * @param userId
     * @param amount
     */
    public void withdraw(String userId, int amount);

    /**
     * 查询账户变更记录
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<UserAccountEntity> selectLogList(String userId, int pageNo, int pageSize, String type);

    /**
     * 捐赠金额，
     * @param userId 用户ID
     * @param welfareId 公益项目ID
     * @param amount 捐赠金额
     */
    public void donate(String userId,String welfareId,String amount);}
