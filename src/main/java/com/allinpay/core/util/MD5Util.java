package com.allinpay.core.util;

import com.alibaba.fastjson.JSON;
import com.allinpay.repository.domain.Admin;

import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;


public class MD5Util {
    private MD5Util() {
    }

    /**
     * Json字符串转java实体类
     */
    public static <T> T json2Obj(String jsonstr, Class<T> cls) {
        return JSON.parseObject(jsonstr, cls);
    }

    /**
     * md5 加密
     *
     * @param data
     * @return String
     */
    public static String md5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(data.getBytes("utf-8"));
            byte[] hash = md.digest();
            StringBuffer outStrBuf = new StringBuffer(32);
            for (int i = 0; i < hash.length; i++) {
                int v = hash[i] & 0xFF;
                if (v < 16) {
                    outStrBuf.append('0');
                }
                outStrBuf.append(Integer.toString(v, 16).toLowerCase());
            }
            return outStrBuf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 获取sign
     *
     * @param params
     * @param key
     * @return String
     */
    public static String sign(TreeMap<String, String> params, String key) {
        params.put("key", key);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null && entry.getValue().length() > 0) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println("明文:" + sb.toString());
        String sign = md5(sb.toString()).toUpperCase();
        System.out.println("密文:" + sign);
        params.remove("key");
        return sign;
    }

    /**
     * md5 验签
     *
     * @param param
     * @param appkey
     * @return
     * @throws Exception
     */
    public static boolean validSign(TreeMap<String, String> param, String appkey) throws Exception {
        if (param != null && !param.isEmpty()) {
            if (!param.containsKey("sign")) {
                return false;
            }
            param.put("key", appkey);//将分配的appkey加入排序
            StringBuilder sb = new StringBuilder();
            String sign = param.get("sign").toString();
            param.remove("sign");
            for (String key : param.keySet()) {
                String value = param.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            String blank = sb.toString();
            System.out.println(blank + ";" + sign);
            return sign.toLowerCase().equals(md5(blank));
        }
        return false;
    }

    public static void main(String[] args) {
        TreeMap<String, String> paramMap = new TreeMap<>();
        String key = "mykey";
        paramMap.put("aa", "no1");
        paramMap.put("bb", "no2");
        paramMap.put("cc", "no2");
        paramMap.put("dd", "no2");
        String sign = sign(paramMap, key);
        System.out.println(sign);
        paramMap.put("sign", sign);
        try {
            System.out.println(validSign(paramMap, key));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Admin admin = new Admin();
        admin.setKid("213");
        admin.setNickname("小明");
        System.out.println(json2Obj(JSON.toJSONString(admin), Admin.class));
        System.out.println(json2Obj(JSON.toJSONString(paramMap), TreeMap.class));
    }
}
