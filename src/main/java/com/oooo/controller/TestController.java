package com.oooo.controller;

import com.oooo.service.TestService;
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

    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service.testService();
        System.out.println("controller");

        RequestDispatcher rd = request.getRequestDispatcher("../test.jsp");
        rd.forward(request,response);
    }
}
