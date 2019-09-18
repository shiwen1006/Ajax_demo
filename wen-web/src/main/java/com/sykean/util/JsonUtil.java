package com.sykean.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lee on 17/9/4.
 */
public class JsonUtil {

    public static <T> T jsonToObject(String objStr, Class<T> c) {
        if (objStr != null && !"".equals(objStr)) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(objStr, c);
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> List<T> jsonToList(String objStr, Class<T> c) {
        if (objStr != null && !"".equals(objStr)) {
            try {
                List<T> list;
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, c);
                list = mapper.readValue(objStr, javaType);
                return list;
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static String objectToJson(Object o) {
        return objectToJson(o, false);
    }

    public static String objectToJson(Object o, boolean escapeNonAscii) {
        StringWriter str = new StringWriter();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (escapeNonAscii) {
                objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            }
            objectMapper.writeValue(str, o);
            return str.toString();
        } catch (Exception var3) {
            return null;
        }
    }

    /**
     * json字符串转map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        return JSON.parseObject(json, HashMap.class);
    }

    public static String beanToJsonWithJsonProperty(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T jsonToBeanWithJsonProperty(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}
