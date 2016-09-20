package com.oooo.controller;

import com.oooo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/9/18.
 */
@Controller
@RequestMapping("home")
public class TestController {

    @Autowired
    TestService service;

    @RequestMapping("/test")
    public void test(){
        service.testService();
    }
}
