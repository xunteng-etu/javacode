package com.edu.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 签名工具类
 */
public class SignUtils {
    /**
     * 生成签名
     *
     * @param map
     * @param key
     * @return
     */
    public static String getSign(Map<String, Object> map, String key) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (null != entry.getValue() && entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        result = DigestUtils.md5Hex(result).toUpperCase();
        return result;
    }

    public static void main(String[] ages) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("client_id", "12345");
        map.put("client_secret", "123456");
        String key = "f924f68726924ec0a827deac0896a4ff";
        System.out.println(SignUtils.getSign(map, key));
    }
}
