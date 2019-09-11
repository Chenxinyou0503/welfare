package com.welfare.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.welfare.dao.*;
import com.welfare.entity.*;
import com.welfare.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 13:32
 * @Description:
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAccountLogDao userAccountLogDao;

    @Autowired
    private WelfareDao welfareDao;

    @Autowired
    private WelfareLogDao welfareLogDao;

    /**
     * 充值功能
     * 1.余额增加
     * 2.增加记录
     * 3.调用布比接口
     *
     * @param userId 用户ID
     * @param amount 金额
     */
    @Override
    public JSONObject recharge(long userId, int amount) {
        JSONObject jsonObject = new JSONObject();
        UserAccountEntity userAccountEntity = userAccountDao.selectByUserId(userId);
        if (StringUtils.isEmpty(userAccountEntity)) {
            jsonObject.put("code", "error");
            jsonObject.put("msg", "账号不存在");
        }
        long userMoney = userAccountEntity.getMoney();
        long balance = userMoney + amount;
        userAccountDao.updateUserAccount(balance, userId);
        UserAccountLogEntity userAccountLogEntity = new UserAccountLogEntity();
        userAccountLogEntity.setAmount(amount);
        userAccountLogEntity.setCreateTime(System.currentTimeMillis());
        userAccountLogEntity.setType("1");
        userAccountLogEntity.setUserId(Long.parseLong(userAccountEntity.getUserId()));
        userAccountLogDao.insertSelective(userAccountLogEntity);
        jsonObject.put("code", "SUCCESS");
        jsonObject.put("msg", "充值成功");
        return jsonObject;
    }

    /**
     * 提现功能
     * 1.余额减少
     * 2.增加余额记录
     * 3. 减少布比账号余额
     *
     * @param userId
     * @param amount
     */
    @Override
    public JSONObject withdraw(long userId, int amount) {
        JSONObject jsonObject = new JSONObject();
        UserAccountEntity userAccountEntity = userAccountDao.selectByUserId(userId);
        if (StringUtils.isEmpty(userAccountEntity)) {
            jsonObject.put("code", "error");
            jsonObject.put("msg", "账号不存在");
        }
        long userMoney = userAccountEntity.getMoney();
        long balance = userMoney - amount;
        if (balance < 0) {
            jsonObject.put("code", "error");
            jsonObject.put("msg", "账号余额不足");
        } else {
            userAccountDao.updateUserAccount(balance, userId);
            UserAccountLogEntity userAccountLogEntity = new UserAccountLogEntity();
            userAccountLogEntity.setAmount(amount);
            userAccountLogEntity.setCreateTime(System.currentTimeMillis());
            userAccountLogEntity.setType("3");
            userAccountLogEntity.setUserId(Long.parseLong(userAccountEntity.getUserId()));
            userAccountLogDao.insertSelective(userAccountLogEntity);
            jsonObject.put("code", "SUCCESS");
            jsonObject.put("msg", "提现成功");
        }
        return jsonObject;
    }

    @Override
    public PageInfo<UserAccountLogEntity> selectLogList(long userId, int pageNo, int pageSize, String type) {
        PageInfo<UserAccountLogEntity> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> {
            Integer state = 0;
            if (StringUtils.isEmpty(type)) {
                state = Integer.parseInt(type);
            }
            userAccountLogDao.selectListByState(state, userId);
        });
        return pageInfo;
    }

    /**
     * 1.先取项目，
     * 2.判断用户账号是否足够，减少余额，
     * 3.判断项目金额是否达到目标，
     * 4.增加项目金额，判断金额是否到目标，是，更改项目状态，
     * 5.调用布比接口
     * 6.增加用户捐款记录，增加项目捐款记录
     *
     * @param userId    用户ID
     * @param welfareId 公益项目ID
     * @param amount    捐赠金额
     */
    @Override
    public JSONObject donate(long userId, String welfareId, int amount) {
        JSONObject jsonObject = new JSONObject();
        WelfareEntity welfareEntity = welfareDao.selectWelfareOne(welfareId);
        UserAccountEntity userAccountEntity = userAccountDao.selectByUserId(userId);
        if (StringUtils.isEmpty(userAccountEntity)) {
            jsonObject.put("code", "error");
            jsonObject.put("msg", "账号余额不足");
        }
        if (StringUtils.isEmpty(welfareEntity)) {
            jsonObject.put("code", "error");
            jsonObject.put("msg", "项目不存在");
        }
        long userMoney = userAccountEntity.getMoney();
        if (userMoney > amount) {
            long balance = userMoney - amount;
            userAccountDao.updateUserAccount(balance, userId);
            UserAccountLogEntity userAccountLogEntity = new UserAccountLogEntity();
            userAccountLogEntity.setAmount(amount);
            userAccountLogEntity.setCreateTime(System.currentTimeMillis());
            userAccountLogEntity.setWelfareId(welfareId);
            userAccountLogEntity.setWelfareName(welfareEntity.getWelfareName());
            userAccountLogEntity.setType("2");
            userAccountLogEntity.setUserId(Long.parseLong(userAccountEntity.getUserId()));
            userAccountLogDao.insertSelective(userAccountLogEntity);
            long total = welfareEntity.getWelfareActualAccount() + amount;
            welfareDao.updateWelfareAccount(welfareEntity.getId(), total);
            if (total > welfareEntity.getWelfareAccount()) {
                welfareDao.updateStatus(welfareEntity.getId() + "", "3");
            }
            WelfareLogEntity welfareLogEntity = new WelfareLogEntity();
            welfareLogEntity.setCode(welfareEntity.getBuHash());
            welfareLogEntity.setCreateTime(System.currentTimeMillis());
            welfareLogEntity.setWelfareId(welfareEntity.getId());
            welfareLogEntity.setWelfareTitle(welfareEntity.getWelfareTitle());
            UserEntity param = new UserEntity();
            param.setId(userId);
            UserEntity userEntity = userDao.selectOne(param);
            welfareLogEntity.setWelfareSponsor(userEntity.getUsername());
            welfareLogDao.insertSelective(welfareLogEntity);
            jsonObject.put("code", "SUCCESS");
            jsonObject.put("msg", "捐赠成功");
        }
        return jsonObject;
    }

    @Override
    public UserAccountEntity selectUserAccount(long userId) {
        return userAccountDao.selectByUserId(userId);
    }


}
