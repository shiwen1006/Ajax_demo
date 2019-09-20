package com.sykean.controller;

import com.sykean.entity.City;
import com.sykean.util.CityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("city")
@Controller
public class CityController {

    //http://localhost:8080/city/index
    @GetMapping("index")
    public String index() {
        return "t_02";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    //http://localhost:8080/city/person
    @GetMapping("person")
    public String person() {
        return "person";
    }


    @ResponseBody
    @GetMapping("province")
    public List<City> province() {
        List<City> all = CityUtil.all();
        List<City> province = new ArrayList<>();
        for (City city : CityUtil.all()) {
            if ("-1".equals(city.getParentId())) {
                province.add(city);
            }
        }
        return province;
    }

    @ResponseBody
    @GetMapping("city/{code}")
    public List<City> province(@PathVariable("code") String code) {
        if (StringUtils.isBlank(code)) {
            throw new RuntimeException("code不能为空");
        }
        List<City> province = new ArrayList<>();
        for (City city : CityUtil.all()) {
            if (code.equals(city.getParentId())) {
                province.add(city);
            }
        }
        return province;
    }
}
