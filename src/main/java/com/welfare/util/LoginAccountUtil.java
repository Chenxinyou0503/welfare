package com.welfare.util;


import com.welfare.entity.UserEntity;

public class LoginAccountUtil {
    private static final ThreadLocal<UserEntity> loginAccountInfo = new ThreadLocal<>();

    public static void setUserEntity(UserEntity account) {
        loginAccountInfo.set(account);
    }

    public static void clearYzAdAccountModel() {
        loginAccountInfo.remove();
    }

    public static UserEntity getUserEntity() {
        UserEntity loginAccount = loginAccountInfo.get();
        if (loginAccount == null) {
            return null;
        }
        return loginAccount;
    }


    /**
     * 获取登录用户id
     *
     * @return
     */
    public static Long getLoginUserId() {
        UserEntity loginAccount = loginAccountInfo.get();
        if (loginAccount == null) {
            return null;
        }
        return loginAccount.getId();
    }

    public static String getLoginUserName() {
        UserEntity loginAccount = loginAccountInfo.get();
        if (loginAccount == null) {
            return "";
        }
        return loginAccount.getUsername();
    }

}
