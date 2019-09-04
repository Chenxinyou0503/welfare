package com.welfare.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.welfare.dao.WelfareDao;
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
}
