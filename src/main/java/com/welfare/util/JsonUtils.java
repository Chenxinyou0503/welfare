package com.welfare.util;

import com.alibaba.fastjson.JSON;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }


    /**
     * 将对象转换为json字符串
     *
     * @param object
     * @return
     */
    public static String convertObjectToString(Object object) {
        if (object == null) {
            throw new IllegalArgumentException(
                    "invalid argument , object mast be not null!");
        }
        String res = null;
        try {
            res = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("object  conver json exception ");
        }
        return res;
    }

    /**
     * 格式化字符串
     *
     * @param object
     * @return
     */
    public static String convertObjectToStringPretty(Object object) {
        if (object == null) {
            throw new IllegalArgumentException(
                    "invalid argument , object mast be not null!");
        }
        String res = null;
        try {
            res = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("object  conver json exception ");
        }
        return res;
    }

    /**
     * 将字符串String jsonStr中的敏感词 (包含在String[] sensitiveWord数组中)屏蔽
     *
     * @param jsonStr       原始字符串
     * @param sensitiveWord 敏感词数组
     * @return
     * @author hejinen
     * @Date 2015-4-17
     */
    public static String sensitiveWordOfString(String jsonStr, String[] sensitiveWord) {
        //如果jsonStr或sensitiveWord为null，则直接返回
        if (jsonStr == null || sensitiveWord == null) {
            return jsonStr;
        }
        try {
            //否则，遍历敏感词，并依次处理
            for (int i = 0; i < sensitiveWord.length; i++) {
                //判断jsonStr中是否包含sensitiveWord[i]这个敏感词
                if (jsonStr.contains(sensitiveWord[i])) {
                    //处理敏感词
                    jsonStr = ShieldSensitiveWord(jsonStr, sensitiveWord[i]);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage() + ",jsonStr=" + jsonStr + ",sensitiveWord=" + sensitiveWord, e);
        }

        return jsonStr;
    }

    /**
     * 使用递归，将字符串String jsonStr中的敏感词String sensitiveWord全部屏蔽
     *
     * @param jsonStr       原始字符串
     * @param sensitiveWord 敏感词
     * @return
     * @author hejinen
     * @Date 2015-4-17
     */
    private static String ShieldSensitiveWord(String jsonStr, String sensitiveWord) {
        try {
            //递归结束条件
            if (!jsonStr.contains(sensitiveWord)) {
                return jsonStr;
            }

            //取得分割符，在日志中有两种分隔符 '&'或','
            String charStr = jsonStr.substring(jsonStr.indexOf(sensitiveWord) - 1, jsonStr.indexOf(sensitiveWord));
            String[] strs1 = jsonStr.split(sensitiveWord, 2);
            String[] strs2 = {};
            if (!"&".equals(charStr)) {
                charStr = ",";
            }
            strs2 = strs1[1].split(charStr, 2);

            String sensitiveValue = "";
            /*
             * 判断敏感词是否为邮箱，
             * 是，则用replaceEMail()处理
             * 否，则用replaceId()处理
             */
            if (strs2[0].contains("@") || strs2[0].contains("%40")) {
                sensitiveValue = JsonUtils.replaceEMail(strs2[0], 4);
            } else {
                sensitiveValue = JsonUtils.replaceId(strs2[0], 3, 4);
            }

            //递归处理敏感词，并重新拼接字符串
            String strs2Last = "";
            if (strs2.length >= 2) {
                strs2Last = strs2[1];
            }
            jsonStr = strs1[0] + sensitiveWord + sensitiveValue + charStr + ShieldSensitiveWord(strs2Last, sensitiveWord);
        } catch (Exception e) {
            logger.error(e.getMessage() + ",jsonStr=" + jsonStr + ",sensitiveWord=" + sensitiveWord, e);
        }
        return jsonStr;
    }

    /**
     * 将Map<String,String[]> map转换为json字符串,并且屏蔽敏感词(包含在String[] sensitiveWord数组中)
     *
     * @param map
     * @return
     * @author hejinen
     * @Date 2015-4-17
     */
    public static String sensitiveWordOfMap(Map<String, String[]> map, String[] sensitiveWord) {
        String res = null;
        try {
            if (map == null) {
                return null;
            }
            //复制map
            Map<String, String> writeMap = getWriteMap(map);
            //如果sensitiveWord为null则抛出异常
            if (sensitiveWord != null) {
                //遍历敏感词，并依次处理
                for (int i = 0; i < sensitiveWord.length; i++) {
                    if (writeMap.containsKey(sensitiveWord[i])) {
                        /*
                         * 判断敏感词是否为邮箱，
                         * 是，则用replaceEMail(String str)处理
                         * 否，则用replaceId(String str)处理
                         */
                        if (writeMap.get(sensitiveWord[i]).contains("@")) {
                            writeMap.put(sensitiveWord[i], replaceEMail(writeMap.get(sensitiveWord[i]), 3));
                        } else {
                            writeMap.put(sensitiveWord[i], replaceId(writeMap.get(sensitiveWord[i]), 2, 3));
                        }
                    }

                }
            }
            res = objectMapper.writeValueAsString(writeMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return res;
    }

    /**
     * 将Map map转换为json字符串,并且屏蔽敏感词(包含在String[] sensitiveWord数组中)
     *
     * @param map
     * @return
     * @author hejinen
     * @Date 2015-4-17
     */
    public static String sensitiveWordOfMap2(Map map, String[] sensitiveWord) {
        String res = null;
        try {
            if (map == null) {
                return null;
            }
            //复制map
            Map<String, String> writeMap = getWriteMap(map);
            //如果sensitiveWord为null则抛出异常
            if (sensitiveWord != null) {
                //遍历敏感词，并依次处理
                for (int i = 0; i < sensitiveWord.length; i++) {
                    if (writeMap.containsKey(sensitiveWord[i])) {
                        /*
                         * 判断敏感词是否为邮箱，
                         * 是，则用replaceEMail(String str)处理
                         * 否，则用replaceId(String str)处理
                         */
                        if (writeMap.get(sensitiveWord[i]).contains("@")) {
                            writeMap.put(sensitiveWord[i], replaceEMail(writeMap.get(sensitiveWord[i]), 3));
                        } else {
                            writeMap.put(sensitiveWord[i], replaceId(writeMap.get(sensitiveWord[i]), 2, 3));
                        }
                    }
                }
            }
            res = objectMapper.writeValueAsString(writeMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return res;
    }

    /**
     * 用*替换buyer_id,seller_id的中间部分，只留取前m位和后n位
     *
     * @param str
     * @return
     * @author hejinen
     * @Date 2015-4-17
     */
    private static String replaceId(String str, int m, int n) {
        StringBuilder st = new StringBuilder();
        st.append(str.substring(0, m));
        st.append("*");
        st.append(str.substring(str.length() - n));
        return st.toString();
    }

    /**
     * 用*替换buyer_email,seller_email的中间部分，只留取前m位和@符号后面的部分或者%40(@在浏览器中是这样显示的，有的日志直接打印)后面的部分
     *
     * @param str
     * @return
     * @author hejinen
     * @Date 2015-4-17
     */
    private static String replaceEMail(String str, int m) {
        if (str == null || !(str.contains("@") || str.contains("%40"))) {
            return str;
        }
        String charStr = "@";
        if (!str.contains("@")) {
            charStr = "%40";
        }

        String[] strs = str.split(charStr);
        if (strs[0].length() > m) {
            return strs[0].substring(0, m) + "*" + charStr + strs[1];
        } else {
            return strs[0] + "*" + charStr + strs[1];
        }
    }

    /**
     * request.getParameterMap()取得的map是只读的，将它变为普通的map
     *
     * @param properties
     * @return
     * @author hejinen
     * @Date 2015-4-17
     */
    private static Map getWriteMap(Map properties) {
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 将json字符串转换为 对象
     *
     * @param jsonData
     * @return
     */
    public static <T> T convertStringToObject(String jsonData,
                                              Class<T> classType) {
        if (jsonData == null || jsonData.trim().length() == 0) {
            throw new IllegalArgumentException("jsonData is empty");
        }
        if (classType == null) {
            throw new IllegalArgumentException("classType is empty");
        }
        T res = null;
        try {
            res = (T) objectMapper.readValue(jsonData, classType);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("json conver object exception ");
        }
        return res;
    }

    public static <T> List<T> convertStringToListObject(String jsonData,
                                                        Class<T> classType) {
        if (jsonData == null || jsonData.trim().length() == 0) {
            throw new IllegalArgumentException("jsonData is empty");
        }
        if (classType == null) {
            throw new IllegalArgumentException("classType is empty");
        }
        try {
            List<T> list = JSON.parseArray(jsonData, classType);
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("json conver object exception ");
        }
    }

    public static <K, V> Map<K, V> toMap(String jsonData, K key, V value) {
        try {
            return objectMapper.readValue(jsonData,
                    objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, key.getClass(), value.getClass()));
        } catch (IOException e) {
            logger.error("toMap error", e);
            throw new RuntimeException("json conver object exception ");
        }
    }

    public static <T> void builder(StringBuilder builder, T[] params) {
        if (params != null) {
            builder.append("params:").append("[");
            for (int i = 0; i < params.length; i++) {
                builder.append(params[i]);
                if (i != params.length - 1) {
                    builder.append(",");
                }
            }
            builder.append("]").append("}");
        }
    }
}
