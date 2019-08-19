package com.welfare.service;

import com.welfare.entity.UserEntity;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:59
 * @Description: 用户注册，登录
 */
public interface UserService {
    public String register(String username, String password, String phone);

    public UserEntity login(String username, String password);

    /**
     *
     * @param username
     * @return
     */
    public UserEntity queryOne(String username);

    /**
     * 根据用户名修改密码
     * @param username
     * @param password
     * @param newPassword
     * @return
     */
    public String updatePassword(String username, String password, String newPassword);
}
