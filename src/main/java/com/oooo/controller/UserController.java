package com.oooo.controller;

import com.oooo.model.User;
import com.oooo.service.UserService;
import com.oooo.util.Constant;
import com.oooo.util.RespMsg;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;

/**
 * Created by chenpan on 17-1-8.
 */
@Controller
@RequestMapping("/agent")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserService userService;

    @RequestMapping("/userInfo")
    public RespMsg<User> getUserInfo(HttpServletRequest request, Model model){
        RespMsg<User> respMsg = new RespMsg<>();
        Integer userId = (Integer) request.getSession().getAttribute(Constant.getInstance().USER_ID);

        User user = userService.findById(userId);

        if (user == null){
            respMsg.setCode(201);
            respMsg.setMsg("用户不存在");
            return respMsg;
        }
        user.setPassword("xxxx");

        respMsg.setCode(200);
        respMsg.setResult(user);
        return respMsg;

    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public RespMsg<String> updatePassword(HttpServletRequest request,
                                           @RequestParam(value = "password",required = true) String password){

        RespMsg<String> respMsg = new RespMsg<>();
        if (StringUtils.isEmpty(password)){
            respMsg.setCode(201);
            respMsg.setMsg("密码不能为空");
            return respMsg;
        }

        if (StringUtils.length(password) > 16){
            respMsg.setCode(201);
            respMsg.setMsg("密码不能超过16位数");
            return respMsg;
        }
        Integer userId = (Integer) request.getSession().getAttribute(Constant.getInstance().USER_ID);

        User user = userService.findById(userId);

        if (user == null){
            respMsg.setCode(201);
            respMsg.setMsg("用户不存在");
            return respMsg;
        }


        return respMsg;
    }
}
