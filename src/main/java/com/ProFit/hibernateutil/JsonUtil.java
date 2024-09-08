package com.ProFit.hibernateutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // 註冊 Hibernate6 模組，處理 Hibernate 的代理對象
        mapper.registerModule(new Hibernate6Module());
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
