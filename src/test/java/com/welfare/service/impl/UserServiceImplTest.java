package com.welfare.service.impl;

import com.welfare.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 15:18
 * @Description:
 */
public class UserServiceImplTest extends WelfareTest {

    @Autowired
    private UserService userService;

    @Test
    public void register() {
        userService.register("qwer", "123456", "123456789");
    }

    @Test
    public void login() {
        userService.login("qwer", "123456");
    }

    @Test
    public void queryOne() {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void update() {
    }
}