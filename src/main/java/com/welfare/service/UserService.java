package com.welfare.service;

import com.welfare.entity.UserEntity;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:59
 * @Description: 用户注册，登录
 */
public interface UserService {

    /**
     * 注册
     * @param username
     * @param password
     * @param phone
     * @return
     */
    public String register(String username, String password, String phone);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public UserEntity login(String username, String password);

    /** 根据用户名查询用户信息
     * @param username
     * @return
     */
    public UserEntity queryOne(String username);

    /**
     * 根据用户名修改密码
     *
     * @param username
     * @param password
     * @param phone
     * @return
     */
    public String updatePassword(String username, String password, String phone);

    /**
     * 修改用户信息
     *
     * @param userEntity
     */
    public void update(UserEntity userEntity);
}
