package com.welfare.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class CookieConfig {

    /**
     * cookie域名后缀
     */
    public static String cookieDomainSuffix;
    /**
     * cookie加密值
     */
    public static String cookieEncryptKey;


    public String getCookieDomainSuffix() {
        return cookieDomainSuffix;
    }

    @Value("${welfare.hostname.url}")
    public void setCookieDomainSuffix(String cookieDomainSuffix) {
        CookieConfig.cookieDomainSuffix = cookieDomainSuffix;
    }

    public String getCookieEncryptKey() {
        return cookieEncryptKey;
    }

    @Value("${welfare.cookie.encryptKey}")
    public void setCookieEncryptKey(String cookieEncryptKey) {
        CookieConfig.cookieEncryptKey = cookieEncryptKey;
    }

}
