package com.sykean.util;

import cn.yueshutong.file.ClassPathResource;
import com.sykean.entity.City;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class CityUtil {

    public static List<City> all() {
        ClassPathResource resource = new ClassPathResource();
        String citys = resource.readString("city.json", StandardCharsets.UTF_8);
        return JsonUtil.jsonToList(citys, City.class);
    }
}
