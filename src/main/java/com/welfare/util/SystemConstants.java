/**
 * Yunzongnet.com Inc.
 * Copyright (c) 2016-2016 All Rights Reserved.
 */
package com.welfare.util;

/**
 * 系统常量
 */
public class SystemConstants {

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final String LOGIN_TOKEN = "D_S_P_L_T";
    public static final String LOGIN_SIGN = "D_S_P_L_S";
    public static final String LOGIN_SIGN_VALUE = "1";
    public static final String LOGIN_MD5 = "D_S_P_L_MD5";
    public static final String LOGIN_CODE="D_S_P_CODE";
    /**
     * 未登录
     */
    public static final Integer LOGIN_FAILURE = 301;
    public static final String SPOT = ".";


    /**
     * 没有权限
     */
    public static final int PERMISSION_DENIED = 302;

    /**
     * 环境变量 hostName
     */
    public static String HOST_NAME = "HOSTNAME";

    /**
     * 超级管理员 角色id
     */
    public static Long SUPER_MANAGER_ROLE_ID = 1L;
    public static String SUPER_MANAGER_ACCOUNT_ID = "YZ0000";
    public static String SUPER_MANAGER_ACCOUNT_NAME = "超级管理员";
}
