package com.welfare.service;

import com.welfare.entity.WelfareEntity;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 15:01
 * @Description:
 */
public interface WelfareService {
    /**
     * 添加项目
     *
     * @param entity
     */
    public void save(WelfareEntity entity);

    /**
     * 修改项目
     *
     * @param entity
     */
    public void update(WelfareEntity entity);

    /**
     * 审核项目状态
     *
     * @param welfareId
     */
    public void updateStatus(String welfareId);

    public List<WelfareEntity> selectListByAdmin(int pageNo, int pageSize, String type);

    public List<WelfareEntity> selectListByUser(int pageNo, int pageSize, String userId);
}
