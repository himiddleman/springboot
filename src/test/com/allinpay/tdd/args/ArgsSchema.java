package com.allinpay.tdd.args;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ArgsSchema {
    private Map<String, String> schemaMap;

    public ArgsSchema(String schemaConfig) {
        schemaMap = new HashMap<>();
        Arrays.asList(schemaConfig.split(",")).stream().forEach(pair -> {
            String[] singleSchema = pair.split(":");
            schemaMap.put(singleSchema[0], singleSchema[1]);
        });
    }


    public Object getValue(String flag, String value) {
        String flagType = schemaMap.get(flag);
        switch (flagType) {
            case "bool":
                if (StringUtils.isBlank(value)) {
                    return Boolean.FALSE;
                }
                if (!("true".equalsIgnoreCase(value) ||
                        "false".equalsIgnoreCase(value))) {
                    throw new IllegalArgumentException("布尔类型的参数必须是true或者false");
                }
                return "true".equals(value);
            case "int":
                if (StringUtils.isBlank(value)) {
                    return 0;
                }
                try {
                    return Integer.parseInt(value);
                } catch (Exception e) {
                    throw new IllegalArgumentException("转换为整型数异常");
                }
            case "string":
                return value;
            case "slist":
                if (StringUtils.isBlank(value)) {
                    return new ArrayList<>();
                }
                return Arrays.asList(value.split(","));
            case "ilist":
                if (StringUtils.isBlank(value)) {
                    return new ArrayList<>();
                }
                List list = new ArrayList<>();
                Arrays.asList(value.split(",")).stream().forEach(ele -> {
                    list.add(Integer.parseInt(ele));
                });
                return list;
            default:
                throw new IllegalArgumentException("不支持的参数类型");
        }
    }
}
