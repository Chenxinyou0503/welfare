package com.welfare.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.welfare.dao.UserAccountDao;
import com.welfare.dao.UserAccountLogDao;
import com.welfare.dao.UserDao;
import com.welfare.dao.WelfareDao;
import com.welfare.entity.UserAccountEntity;
import com.welfare.entity.UserAccountLogEntity;
import com.welfare.entity.UserEntity;
import com.welfare.entity.WelfareEntity;
import com.welfare.service.WelfareService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 13:34
 * @Description:
 */
@Service
public class WelfareServiceImpl implements WelfareService {
    @Autowired
    private WelfareDao welfareDao;

    @Autowired
    private UserAccountDao userAccountDao;
    @Autowired
    private UserAccountLogDao userAccountLogDao;
    @Autowired
    private UserDao userDao;

    /**
     * 添加项目
     *
     * @param entity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(WelfareEntity entity) {
        //TODO 此处添加生成布比项目接口
        entity.setCreateTime(new Date());
        entity.setState(1);
        entity.setWelfareActualAccount(0);
        welfareDao.insertSelective(entity);
    }

    @Override
    public void update(WelfareEntity entity) {
        welfareDao.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void updateStatus(String welfareId, String state) {
        welfareDao.updateStatus(welfareId, state);
    }

    @Override
    public PageInfo<WelfareEntity> selectListByAdmin(int pageNo, int pageSize, String type) {

        PageInfo<WelfareEntity> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> {
            Integer state = 0;
            if (StringUtils.isNotBlank(type)) {
                state = Integer.parseInt(type);
            }
            welfareDao.selectListByState(state);
        });
        return pageInfo;
    }

    @Override
    public PageInfo<WelfareEntity> selectListByUser(int pageNo, int pageSize, String userId) {
        PageInfo<WelfareEntity> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> {
//            welfareDao.selectListByState(userId);
        });
        return pageInfo;
    }

    @Override
    public PageInfo<WelfareEntity> selectListByIndex(int pageNo, int pageSize) {
        int type = 2;
        PageInfo<WelfareEntity> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> {
            welfareDao.selectListByState(type);
        });
        return pageInfo;
    }

    @Override
    public JSONObject settlement(String welfareId) {
        JSONObject jsonObject = new JSONObject();
        WelfareEntity welfareEntity = welfareDao.selectWelfareOne(welfareId);
        if (welfareEntity != null) {
            if (welfareEntity.getState() == 3) {
                UserEntity userEntity = userDao.queryOne(welfareEntity.getWelfareName());
                UserAccountEntity userAccountEntity = userAccountDao.select(userEntity.getId());
                long total = userAccountEntity.getMoney() + welfareEntity.getWelfareActualAccount();
                userAccountDao.updateUserAccount(total, userEntity.getId());
                //TODO 调用布比接口
                UserAccountLogEntity userAccountLogEntity = new UserAccountLogEntity();
                userAccountLogEntity.setAmount(welfareEntity.getWelfareActualAccount());
                userAccountLogEntity.setCreateTime(System.currentTimeMillis());
                userAccountLogEntity.setType("4");
                userAccountLogEntity.setUserId(userEntity.getId());
                userAccountLogDao.insertSelective(userAccountLogEntity);
                jsonObject.put("code", "SUCCESS");
                jsonObject.put("msg", "结算成功");
            } else {
                jsonObject.put("code", "error");
                jsonObject.put("msg", "项目不能结算");
            }
        } else {
            jsonObject.put("code", "error");
            jsonObject.put("msg", "项目不存在");
        }
        return jsonObject;
    }
}
