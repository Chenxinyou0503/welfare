package com.welfare.service;

import com.welfare.entity.UserEntity;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:59
 * @Description:
 */
public interface UserService {
    public String register(String username, String password, String phone);

    public UserEntity login(String username, String password);

    public UserEntity queryOne(String username);
}
