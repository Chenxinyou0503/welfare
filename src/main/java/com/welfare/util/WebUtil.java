package com.welfare.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class WebUtil {
    /**
     * 前端返回json对象
     * @param response
     * @param jsonContent
     */
    public static void writeJson2Client(HttpServletResponse response, String jsonContent) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            response.getWriter().write(jsonContent);
            response.getWriter().flush();
            response.getWriter().close();
        } catch(Exception e) {
        }
    }

    /**
     * 获取全部cookie
     * @param request
     * @return
     */
    public static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
        return cookieMap;
    }
}
