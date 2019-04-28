package com.lzx.hsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(value = "*")
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String homeData(){
        return "index";
    }
}
