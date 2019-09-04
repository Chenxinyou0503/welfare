package com.welfare.service.impl;

import com.welfare.entity.UserAccountEntity;
import com.welfare.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 13:32
 * @Description:
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
    /**
     * 充值功能
     * @param userId 用户ID
     * @param amount 金额
     */
    @Override
    public void recharge(String userId, int amount) {

    }

    @Override
    public void withdraw(String userId, int amount) {

    }

    @Override
    public List<UserAccountEntity> selectLogList(String userId, int pageNo, int pageSize, String type) {
        return null;
    }

    @Override
    public void donate(String userId, String welfareId, String amount) {

    }
}
