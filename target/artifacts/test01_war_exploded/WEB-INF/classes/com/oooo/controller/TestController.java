package com.oooo.controller;

import com.oooo.service.PlayerRechargeInfoService;
import com.oooo.service.TestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/9/18.
 */
@Controller
@RequestMapping("home")
public class TestController {

    @Autowired
    TestService service;
    @Autowired
    PlayerRechargeInfoService playerRechargeInfoService;

    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        service.testService();
        System.out.println("controller");
        playerRechargeInfoService.getAll();

      /*  Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = factory.getInstance();

        SecurityUtils.setSecurityManager(manager);


        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        RequestDispatcher rd = request.getRequestDispatcher("../test.jsp");
        rd.forward(request,response);*/
    }
}
