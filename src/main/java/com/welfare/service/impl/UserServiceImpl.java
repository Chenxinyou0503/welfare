package com.welfare.service.impl;

import com.welfare.dao.UserAccountDao;
import com.welfare.dao.UserDao;
import com.welfare.entity.UserAccountEntity;
import com.welfare.entity.UserEntity;
import com.welfare.service.UserService;
import com.welfare.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 15:01
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public String register(String username, String password, String phone) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(phone)) {
            return "";
        }
        String md5 = MD5Util.getMD5(password);
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(md5);
        entity.setPhone(phone);
        int result = userDao.insertSelective(entity);
        entity = userDao.queryOne(username);
        //TODO 调用布比接口，生成用户账号code
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setUserId(String.valueOf(entity.getId()));
        userAccountEntity.setMoney(0);
        userAccountEntity.setCode("");
        userAccountDao.insert(userAccountEntity);
        return "SUCCESS";
    }

    @Override
    public UserEntity login(String username, String password) {

        UserEntity entity = userDao.queryOne(username);
        if (StringUtils.isEmpty(entity)) {
            return null;
        }
        return entity;
    }

    @Override
    public UserEntity queryOne(String username) {
        UserEntity param = new UserEntity();
        param.setUsername(username);
        UserEntity entity = userDao.selectOne(param);
        return entity;
    }

    @Override
    public String updatePassword(String username, String password, String newPassword) {
        String md5 = MD5Util.getMD5(password);
        UserEntity param = new UserEntity();
        param.setUsername(username);
        UserEntity entity = userDao.selectOne(param);
        if (md5.equals(entity.getPassword())) {
            String md5New = MD5Util.getMD5(newPassword);
            entity.setPassword(md5New);
            userDao.updatePassword(entity.getId(), md5New);
            return "SUCCESS";
        }
        return "error";
    }

    @Override
    public void update(UserEntity userEntity) {
        userDao.updateByPrimaryKeySelective(userEntity);
    }
}
