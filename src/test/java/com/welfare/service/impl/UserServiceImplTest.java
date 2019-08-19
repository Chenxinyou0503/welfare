package com.welfare.service.impl;

import com.welfare.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 15:18
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void register() {
        userService.register("qwer","123456","123456789");
    }
}