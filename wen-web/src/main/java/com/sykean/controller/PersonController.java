package com.sykean.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sykean.common.PageRes;
import com.sykean.entity.Person;
import com.sykean.mapper.PersonMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("person")
@Controller
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

    @GetMapping("index")
    public String index() {
        return "person";
    }


    @GetMapping("list")
    @ResponseBody
    public PageRes list(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize, String name) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Person> persons = personMapper.selectList(
                new EntityWrapper<Person>()
                        .like(StringUtils.isNotBlank(name), "name", name)
        );
        return PageRes.builder().data(persons).count((int) (new PageInfo<>(persons).getTotal())).build();
    }

    @PostMapping("save")
    @ResponseBody
    public boolean save(@RequestBody Person perosn){
        try {
            personMapper.insert(perosn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
