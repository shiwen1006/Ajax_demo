package com.sykean.controller;

import com.sykean.common.BaseController;
import com.sykean.req.UserReq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController extends BaseController {

    @GetMapping("index")
    public String index(){
        return "t_01";
    }

    @GetMapping("checkUsername")
    @ResponseBody
    public boolean checkUsername(String username){
        if("ven".equals(username)){
            return false;
        }
        return true;
    }

    @PostMapping("login")
    @ResponseBody
    public boolean login(@RequestBody UserReq userReq){
        if("ven".equals(userReq.getUsername())&&"123456".equals(userReq.getPassword())){
            return true;
        }
        return false;
    }
}
