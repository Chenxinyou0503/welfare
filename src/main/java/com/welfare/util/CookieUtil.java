package com.welfare.util;


import com.welfare.entity.UserEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    private static final String MD5_KEY_PREFIX = "DSP_";
    private static final String MD5_KEY_SUFFIX = "WELFARE";
    private static final String tokenSep = "&";
    private static final String STATUS = "STATUS";


    private static String cookieEncryptKey;
    private static String cookieDomainSuffix;

    public static void setCookieDomainSuffix(String cookieDomainSuffix) {
        CookieUtil.cookieDomainSuffix = cookieDomainSuffix;
    }

    public static void setCookieEncryptKey(String cookieEncryptKey) {
        CookieUtil.cookieEncryptKey = cookieEncryptKey;
    }

    /**
     * 设置登录token
     *
     * @param response
     * @param userEntity
     */
    public static void setLoginCookie(HttpServletResponse response, UserEntity userEntity) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        /*
            设置登录token
         */
        String tokenStr = userEntity.getUsername() + tokenSep + userEntity.getEmail() + tokenSep + userEntity.getRole();
        String token = AesUtil.encrypt(tokenStr, cookieEncryptKey);
        addCookie(response, SystemConstants.LOGIN_TOKEN, token, true);

        /*
            设置登录标示，前端使用
         */
        addCookie(response, SystemConstants.LOGIN_SIGN, SystemConstants.LOGIN_SIGN_VALUE, false);

        /*
            设置cookie MD5值 防篡改
         */
        String md5 = getCookieMd5(token, SystemConstants.LOGIN_SIGN_VALUE);
        addCookie(response, SystemConstants.LOGIN_MD5, md5, true);

    }

    public static void setDownloadStatusCookie(HttpServletResponse response, Integer value) {
        addCookie(response, STATUS, String.valueOf(value), false);
    }

    /**
     * 从cookie中获取当前登录人信息
     *
     * @param request
     * @return
     */
    public static UserEntity getLoginAccountFromCookie(HttpServletRequest request) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Map<String, Cookie> cookieMap = WebUtil.readCookieMap(request);
        if (CollectionUtils.isEmpty(cookieMap)) {
            return null;
        }
        if (cookieMap.get(SystemConstants.LOGIN_TOKEN) == null ||
                cookieMap.get(SystemConstants.LOGIN_SIGN) == null
                || cookieMap.get(SystemConstants.LOGIN_MD5) == null) {

            return null;
        }
        String cookieToken = cookieMap.get(SystemConstants.LOGIN_TOKEN).getValue();
        String cookieLoginSign = cookieMap.get(SystemConstants.LOGIN_SIGN).getValue();
        String cookieLoginMd5 = cookieMap.get(SystemConstants.LOGIN_MD5).getValue();
        if (StringUtils.isEmpty(cookieToken)
                || StringUtils.isEmpty(cookieLoginSign)
                || StringUtils.isEmpty(cookieLoginMd5)) {

            return null;
        }
        String tokenStr = AesUtil.decrypt(cookieToken, cookieEncryptKey);
        String[] tokenArr = tokenStr.split(tokenSep);
        if (tokenArr.length != 4) {
            return null;
        }
        String loginSignStr = cookieLoginSign;
        if (!SystemConstants.LOGIN_SIGN_VALUE.equals(loginSignStr)) {
            return null;
        }
        String md5 = getCookieMd5(cookieToken, loginSignStr);
        if (!md5.equals(cookieLoginMd5)) {
            return null;
        }

        UserEntity loginAccount = new UserEntity();
        loginAccount.setUsername(tokenArr[0]);
        return loginAccount;
    }


    /**
     * 删除B端登录cookie
     *
     * @param response
     * @param request
     */
    public static void deleteLoginCookie(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Cookie> map = readCookieMap(request);
        deleteCookie(response, map.get(SystemConstants.LOGIN_TOKEN));
        deleteCookie(response, map.get(SystemConstants.LOGIN_SIGN));
        deleteCookie(response, map.get(SystemConstants.LOGIN_MD5));
        addCookie(response, SystemConstants.LOGIN_SIGN, "0", false);
    }

    /**
     * 根据cookie名称删除指定coolie
     *
     * @param response
     * @param request
     * @param cookieName
     */
    public static void deleteCookieByName(HttpServletResponse response, HttpServletRequest request, String cookieName) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        deleteCookie(response, cookieMap.get(cookieName));
    }

    ;

    /**
     * 删除cookie
     */
    private static void deleteCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            cookie.setDomain(cookieDomainSuffix);
            cookie.setValue(null);
            response.addCookie(cookie);
        }
    }


    /**
     * merchantManage:batchShopImport
     * 创建cookie
     */
    private static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, boolean httpOnly) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(cookieName).append("=").append(cookieValue).append(";");
        if (!StringUtils.isEmpty(cookieDomainSuffix)) {
            buffer.append(" domain=").append(cookieDomainSuffix).append(";");
        }
        buffer.append("path=").append("/").append(";");
        if (httpOnly) {
            buffer.append("HTTPOnly;");
        }

        response.addHeader("Set-Cookie", buffer.toString());
    }

    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
        return cookieMap;
    }

    /**
     * 对cookie值md5
     *
     * @param token
     * @param loginSing
     * @return
     */
    private static String getCookieMd5(String token, String loginSing) {
        StringBuilder builder = new StringBuilder(128);
        builder.append(MD5_KEY_PREFIX).append("_").append(SystemConstants.LOGIN_TOKEN)
                .append("=").append(token).append(";")
                .append(MD5_KEY_PREFIX).append("_").append(SystemConstants.LOGIN_SIGN)
                .append("=").append(loginSing).append(";")
                .append(MD5_KEY_SUFFIX);
        String str = builder.toString();
        return MD5Util.MD5Encode(str, SystemConstants.DEFAULT_CHARSET);
    }
}
