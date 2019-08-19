package com.welfare.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/11 15:23
 * @Description:
 */
public class MD5Util {
    private static Logger loggerFactory = LoggerFactory.getLogger(MD5Util.class);

    public static String getMD5(String str) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(str.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static boolean verfityMd5(String string, String md5) {
        String newMd5 = getMD5(string);
        if (newMd5.equalsIgnoreCase(newMd5)) {
            return true;
        }
        return false;
    }
}
